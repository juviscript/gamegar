package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("username")
    private String username;
    @JsonProperty("birthday")
    private String birthday;
    //private ArrayList<List> ownGame;
    //    private ArrayList<List> favoriteGame;

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

   // public ArrayList<List> getOwnGame() {
    //        return ownGame;
    //    }
    //    public ArrayList<List> getFavoriteGame() {
    //        return favoriteGame;
    //    }
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
   // public void setOwnGame(ArrayList<List> ownGame) {
    //        this.ownGame = ownGame;
    //    }
    //    public void setFavoriteGame(ArrayList<List> favoriteGame) {
    //        this.favoriteGame = favoriteGame;
    //    }
}
