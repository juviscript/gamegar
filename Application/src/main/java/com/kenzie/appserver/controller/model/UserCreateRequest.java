package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserCreateRequest {
    public ArrayList<List> favoriteGame;

    public ArrayList<List> ownGames;
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

    /* ---------------- Getters ---------------- */
    public ArrayList<List> getOwnGames() {
        return ownGames;
    }

    public ArrayList<List> getFavoriteGame() {
        return favoriteGame;
    }

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

    public void setOwnGames(ArrayList<List> ownGames) {
        this.ownGames = ownGames;
    }
    public void setFavoriteGame(ArrayList<List> favoriteGame) {
        this.favoriteGame = favoriteGame;
    }
}