package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class UserUpdateRequest {
    @NotEmpty
    @JsonProperty("name")
    private String name;
    @NotEmpty
    @JsonProperty("email")                                 // I omitted birthday in this request because I don't believe
    private String email;                                  // that you can typically update your birthday after account creation so...

    @NotEmpty
    @JsonProperty("username")
    private String username;


    /* ---------------- Getters ---------------- */

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }



    /* ---------------- Setters ---------------- */

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
