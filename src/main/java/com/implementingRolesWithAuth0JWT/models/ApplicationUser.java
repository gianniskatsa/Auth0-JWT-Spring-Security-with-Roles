package com.implementingRolesWithAuth0JWT.models;

import javax.persistence.*;

@Entity
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public ApplicationUser() {
    }

    public ApplicationUser(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

    }

    public void setId(long id) {
        this.id = id;
    }
}
