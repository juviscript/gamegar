package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("username")
    private String username;
    @JsonProperty("birthday")
    private LocalDate birthday;

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

    public LocalDate getBirthday() {
        return birthday;
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

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
