package com.kenzie.appserver.service.model;

import java.util.LinkedList;
import java.util.UUID;

public class VideoGame {

    public String title;
    public String developer;
    public String genre;

    public static UUID id;       // Create a randomly generated ID.
    public LinkedList<String> platforms;
    public LinkedList<String> tags;
    public String description;

    public VideoGame(String title, String developer, String genre) {
        this.title = title;
        this.developer = developer;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getGenre() {
        return genre;
    }

    public static UUID getId() {
        return id;
    }

    public LinkedList<String> getPlatforms() {
        return platforms;
    }

    public LinkedList<String> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }


}
