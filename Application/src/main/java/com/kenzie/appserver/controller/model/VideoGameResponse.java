package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class VideoGameResponse {

    @JsonProperty("id")
    public String id;

    @JsonProperty("title")
    public String title;
    @JsonProperty("developer")
    public String developer;
    @JsonProperty("genre")
    public String genre;
    @JsonProperty("year")
    public Integer year;
    @JsonProperty("platforms")
    public LinkedList<String> platforms;
    @JsonProperty("tags")
    public LinkedList<String> tags;
    @JsonProperty("description")
    public String description;
    @JsonProperty("country")
    public String country;


    /* ---------------- Getters ---------------- */

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

    public Integer getYear() { return year; }

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



    /* ---------------- Setters ---------------- */

    public void setId (String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(Integer year) { this.year = year; }

    public void setPlatforms(LinkedList<String> platforms) {
        this.platforms = platforms;
    }

    public void setTags(LinkedList<String> tags) {
        this.tags = tags;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountry(String country) { this.country = country; }
}
