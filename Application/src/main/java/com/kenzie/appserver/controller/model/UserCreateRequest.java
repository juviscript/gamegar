package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserCreateRequest {
    @NotEmpty
    @JsonProperty("userId")
    private String userId;
    @NotEmpty
    @JsonProperty("name")
    private String name;
    @NotEmpty
    @JsonProperty("email")
    private String email;
    @NotEmpty
    @JsonProperty("username")
    private String username;
    @NotEmpty
    @JsonProperty("birthday")
    private String birthday;

    private ArrayList<List> ownGame;
    private ArrayList<List> favoriteGame;

    /* ---------------- Getters ---------------- */

    public String getUserId() {
        return userId;
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

    public String getBirthday() {
        return birthday;
    }
    public ArrayList<List> getOwnGame() {
        return ownGame;
    }
    public ArrayList<List> getFavoriteGame() {
        return favoriteGame;
    }


    /* ---------------- Setters ---------------- */

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
   }
    public void setOwnGame(ArrayList<List> ownGame) {
        this.ownGame = ownGame;
    }

    public void setFavoriteGame(ArrayList<List> favoriteGame) {
        this.favoriteGame = favoriteGame;
    }
}

