package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;

@DynamoDBTable(tableName = "VideoGameCatalog")
public class VideoGameCatalogRecord {

    public String id;

    public String title;
    public String developer;
    public String genre;
    public Integer year;
    public LinkedList<String> platforms;
    public LinkedList<String> tags;
    public String description;
    public String country;

    @DynamoDBHashKey(attributeName = "id")
    public String getGameId() {
        return id;}

    @DynamoDBRangeKey(attributeName = "title")
    public String getGameTitle() {
        return title;
    }

    @DynamoDBAttribute(attributeName = "developer")
    public String getDeveloper() {
        return developer;
    }

    @DynamoDBAttribute(attributeName = "genre")
    public String getGenre() {
        return genre;
    }

    @DynamoDBAttribute(attributeName = "year")
    public Integer getYear() { return year; }

    @DynamoDBAttribute(attributeName = "platforms")
    public LinkedList<String> getPlatforms() {
        return platforms;
    }

    @DynamoDBAttribute(attributeName = "tags")
    public LinkedList<String> getTags() {
        return tags;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getGameDescription(){
        return description;
    }

    @DynamoDBAttribute(attributeName = "country")
    public String getCountry() { return country; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCountry(String country) { this.country = country; }

    public void setId(String id) { this.id = id;}

    public void setPlatforms(LinkedList<String> platforms) {
        this.platforms = platforms;
    }

    public void setTags(LinkedList<String> tags) {
        this.tags = tags;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setYear(Integer year) { this.year = year; }

    // TODO: 3/29/2023  Not sure about the overrides!!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoGameCatalogRecord that = (VideoGameCatalogRecord) o;
        return title.equals(that.title) && developer.equals(that.developer) && Objects.equals(genre, that.genre) && Objects.equals(platforms, that.platforms) && Objects.equals(tags, that.tags) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, developer, genre, platforms, tags, description);
    }
}
