package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserUpdateRequest {
    public ArrayList<List> getFavoriteGame;

    public ArrayList<List> getownGames;
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
    public ArrayList<List> getGetFavoriteGame() {
        return getFavoriteGame;
    }
    public ArrayList<List> getGetownGames() {
        return getownGames;
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
    public void setGetFavoriteGame(ArrayList<List> getFavoriteGame) {
        this.getFavoriteGame = getFavoriteGame;
    }
    public void setGetownGames(ArrayList<List> getownGames) {
        this.getownGames = getownGames;
    }
}
