package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CatalogCreateRequest {
    @NotEmpty
    @JsonProperty("title")
    private String title;

    @JsonProperty("developer")
    private String developer;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("description")
    private String description;

    @JsonProperty("country")
    private String country;

    @JsonProperty("platforms")
    private String platforms;

    @JsonProperty("tags")
    private String tags;

    @JsonProperty("image")
    private String image;



//    -------------------------------------- Getters ---------------------------------------

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


//    -------------------------------------- Setters ---------------------------------------

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setImage(String image) {
        this.image = image;
    }
    }
