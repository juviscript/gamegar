package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.UUID;

public class VideoGameCreateRequest {
    @NotEmpty
    @JsonProperty("title")
    public String title;
    @JsonProperty("developer")
    public String developer;
    @JsonProperty("genre")
    public String genre;
    @JsonProperty("platforms")
    public LinkedList<String> platforms;
    @JsonProperty("tags")
    public LinkedList<String> tags;

    /* ---------------- Getters ---------------- */

    public String getTitle() {
        return title;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getGenre() {
        return genre;
    }

    public LinkedList<String> getPlatforms() {
        return platforms;
    }

    public LinkedList<String> getTags() {
        return tags;
    }


    /* ---------------- Setters ---------------- */

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPlatforms(LinkedList<String> platforms) {
        this.platforms = platforms;
    }

    public void setTags(LinkedList<String> tags) {
        this.tags = tags;
    }
}
