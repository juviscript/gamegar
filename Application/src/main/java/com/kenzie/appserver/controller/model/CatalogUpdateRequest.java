package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CatalogUpdateRequest {
    @NotEmpty
    @JsonProperty("id")
    private String id;

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
    private List<String> platforms;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("image")
    private String image;


//    -------------------------------------- Getters ---------------------------------------


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

    public String getCountry() { return country; }

    public List<String> getPlatforms() {
        return platforms;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getImage() { return image; }



//    -------------------------------------- Setters ---------------------------------------

    public void setId(String id) {
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

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
