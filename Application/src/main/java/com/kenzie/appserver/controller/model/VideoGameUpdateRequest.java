package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.UUID;

public class VideoGameUpdateRequest {
    @NotEmpty
    @JsonProperty("title")
    public String title;
    @JsonProperty("developer")
    public String developer;
    @JsonProperty("genre")
    public String genre;

    @NotEmpty
    @JsonProperty("id")
    public static UUID id;
    @JsonProperty("platforms")
    public LinkedList<String> platforms;
    @JsonProperty("tags")
    public LinkedList<String> tags;
    @JsonProperty("description")
    public String description;


    /* ---------------- Getters ---------------- */

    public String getGameTitle() {
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

    public static void setId(UUID id) {
        VideoGameResponse.id = id;
    }

    public void setPlatforms(LinkedList<String> platforms) {
        this.platforms = platforms;
    }

    public void setTags(LinkedList<String> tags) {
        this.tags = tags;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
