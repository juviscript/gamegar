package com.kenzie.appserver.service.model;

import java.time.LocalDate;

public class User {

    private String name;
    private String email;
    private String username;

    private LocalDate birthday;

    public User(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
