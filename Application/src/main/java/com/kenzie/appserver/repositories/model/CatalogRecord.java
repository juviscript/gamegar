package com.kenzie.appserver.repositories.model;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "GameCatalog")
public class CatalogRecord {
    private String id;
    private String title;
    private String developer;
    private String genre;
    private Integer year;
    private String description;
    private List<String> platforms;
    private List<String> tags;

//    --------------------------- Getters w/ DynamoDB Annotation ---------------------------

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
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
    public Integer getYear() {
        return year;
    }
    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }
    @DynamoDBAttribute(attributeName = "platforms")
    public List<String> getPlatforms() {
        return platforms;
    }
    @DynamoDBAttribute(attributeName = "tags")
    public List<String> getTags() {
        return tags;
    }

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

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

//    -------------------------------------- Overrides ---------------------------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogRecord that = (CatalogRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(developer, that.developer) && Objects.equals(genre, that.genre) && Objects.equals(year, that.year) && Objects.equals(description, that.description) && Objects.equals(platforms, that.platforms) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, developer, genre, year, description, platforms, tags);
    }
}


