package com.kenzie.appserver.service.model;


import java.util.List;


public class Game {
    private String id;
    private String title;
    private String developer;
    private String genre;
    private Integer year;
    private String description;
    private String country;
    private String platforms;
    private String tags;
    private String image;

    public Game(String id, String title, String developer, String genre, Integer year, String description, String country,
                String platforms, String tags, String image) {
        this.id = id;
        this.title = title;
        this.developer = developer;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.country = country;
        this.platforms = platforms;
        this.tags = tags;
        this.image = image;
    }

    public String getId() {
        return id;
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

    public Integer getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    public String getPlatforms() {
        return platforms;
    }

    public String getTags() {
        return tags;
    }
    public String getImage() {
        return image;
    }
}
