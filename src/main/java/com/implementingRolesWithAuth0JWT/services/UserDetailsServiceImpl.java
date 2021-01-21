package com.implementingRolesWithAuth0JWT.services;

import com.implementingRolesWithAuth0JWT.models.ApplicationUser;
import com.implementingRolesWithAuth0JWT.repository.ApplicationUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private ApplicationUserRepository applicationUserRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        Role role = applicationUser.getRole(); Here You can call your role model
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); //here you could do something like this
        // authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getType()));
        return new User(applicationUser.getUsername(), applicationUser.getPassword(),authorities );
    }
}
