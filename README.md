# Improving on Auth0's JWT with Spring-Boot tutorial by adding Role Authorities

### For the original tutorial please check [here](https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/)

# How to add Role Functionality

In a real world project we would need to implement a role entity and join the tables. Depending on the connection 
 between the two entities (OneToMany, ManyToMany) we need to insert a Collection of SimpleGrantedAuthority
 to our User.
 
 This need to be done when the method loadUserByUserName returns
 ```return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());```
 
 For the sake of this example we will manually pass it.
 Also we have some commented lines to guide you in a real life situation.
 
 In our case we use the List<SimpleGrantedAuthority> authorities = new ArrayList<>() in UserDetailsServiceImpl.
 
 Then we manually apply to the method loadUserByUserName the following line
 ```
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"))
 ```
 and replace the emptyList with the authorities. I have commented a way to pass it if you have a real Role Entity.
 
 The next step is to change the authentication filter in order to take into account the SimpleGrantedAuthority when
 it creates the token. So we add the line
 ```
            .withSubject(String.valueOf(((User) auth.getPrincipal()).getAuthorities())).
```
Then in the authorization filter UsernamePasswordAuthenticationToken method
if the user exists we add an additional check with user.contains() because getSubject()
returns a String. Depending on the content we then pass the new SimpleGrantedAuthority.

Finally in the WebSecurity Class we add some .hasRole() functionality to check if we are successful.
### How to test

Follow the original post's instructions or if you want you can test it htrough Postman.
Using Postman first sign-up, then log in, copy the token and post at /tasks as the original example shows.
You can also decode your token at jwt.io and check its contents to see the authority that it has.   
