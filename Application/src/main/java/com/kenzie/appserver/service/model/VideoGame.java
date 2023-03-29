package com.kenzie.appserver.service.model;

import java.util.LinkedList;

public class VideoGame {


    public String id;

    public String title;
    public String developer;
    public String genre;
    public Integer year;
    public LinkedList<String> platforms;
    public LinkedList<String> tags;
    public String description;
    public String country;


    public VideoGame(String id, String title, String developer, String genre, Integer year,
                     LinkedList<String> platforms, LinkedList<String> tags, String description, String country) {
        this.id = id;
        this.title = title;
        this.developer = developer;
        this.genre = genre;
        this.year = year;
        this.platforms = platforms;
        this.tags = tags;
        this.description = description;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public String getGameTitle() {
        return title;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getYear() {
        return year;
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

    public String getCountry() {
        return country;
    }
}
