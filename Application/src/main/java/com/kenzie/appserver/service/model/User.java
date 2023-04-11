package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

public class User {

    private String userId;
    private String name;
    private String email;
    private String username;

    private String birthday;

    private ArrayList<List> favoriteGame;
    private ArrayList<List> ownGames;

    public User(String userId, String name, String email, String username, String birthday, ArrayList<List> favoriteGame, ArrayList<List> ownGames) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.birthday = birthday;
        this.favoriteGame = favoriteGame;
        this.ownGames = ownGames;

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

    public ArrayList<List> getFavoriteGames() {
        return favoriteGame;
    }

    public ArrayList<List> getOwnGames() {
        return ownGames;
    }
}
