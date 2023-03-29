package com.kenzie.appserver.service.model;

import java.time.LocalDate;

public class User {

<<<<<<< HEAD
=======
    private String userId;
>>>>>>> origin/juvisbranch
    private String name;
    private String email;
    private String username;

    private LocalDate birthday;

<<<<<<< HEAD
    public User(String name, String email, String username) {
=======
    public User(String userId, String name, String email, String username, LocalDate birthday) {
        this.userId = userId;
>>>>>>> origin/juvisbranch
        this.name = name;
        this.email = email;
        this.username = username;
        this.birthday = birthday;
    }

<<<<<<< HEAD
=======
    public String getUserId() {
        return userId;
    }

>>>>>>> origin/juvisbranch
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
