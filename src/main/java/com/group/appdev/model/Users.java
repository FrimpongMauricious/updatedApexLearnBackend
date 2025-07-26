package com.group.appdev.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This tells JPA to use the database's auto-increment feature
    private int id;

    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


}
