<<<<<<< HEAD
package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@DynamoDBTable(tableName = "VideoGameCatalog")
public class VideoGameCatalogRecord {

    public String id;

    public String title;
    public String developer;
    public String genre;

    public Integer year;
    public List<String> platforms;
    public List<String> tags;
    public String description;
    public String country;


    @DynamoDBHashKey(attributeName = "id")
    public String getGameId() {
        return id;}

    @DynamoDBHashKey(attributeName = "title")
    public String getGameTitle() {
        return title;
    }

    @DynamoDBAttribute(attributeName = "developer")
    public String getDeveloper() {
        return developer;
    }
    @DynamoDBAttribute(attributeName = "genre")
    public String getGenre () {
            return genre;
        }

    @DynamoDBAttribute(attributeName = "platforms")
    public List<String> getPlatforms() {
        return platforms;
    }
    @DynamoDBAttribute(attributeName = "tags")
<<<<<<< HEAD
    public LinkedList<String> getTags () {
            return tags;
        }
=======
    public List<String> getTags() {
        return tags;
    }

>>>>>>> 4e24074 (Changed birthday to String and LinkedList to List)
    @DynamoDBAttribute(attributeName = "description")
    public String getGameDescription () {
        return description;
    }
    @DynamoDBAttribute(attributeName = "country")
    public String getCountry() { return country; }
    @DynamoDBAttribute(attributeName = "year")
    public Integer getYear(){
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDeveloper (String developer){
        this.developer = developer;
    }

    public void setGenre (String genre){
        this.genre = genre;
    }
    public void setCountry(String country) { this.country = country; }

    public void setId(String id) { this.id = id;}

<<<<<<< HEAD
    public void setPlatforms (LinkedList < String > platforms) {
        this.platforms = platforms;
    }


    public void setTags (LinkedList < String > tags) {
=======
    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public void setTags(List<String> tags) {
>>>>>>> 4e24074 (Changed birthday to String and LinkedList to List)
        this.tags = tags;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public void setYear(Integer year) { this.year = year; }

    @Override
     public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoGameCatalogRecord that = (VideoGameCatalogRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(developer, that.developer) && Objects.equals(genre, that.genre) && Objects.equals(year, that.year) && Objects.equals(platforms, that.platforms) && Objects.equals(tags, that.tags) && Objects.equals(description, that.description) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, developer, genre, year, platforms, tags, description, country);
    }
}
=======
//package com.kenzie.appserver.repositories.model;
//
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
//@DynamoDBTable(tableName = "VideoGameCatalog")
//public class VideoGameCatalogRecord {
//
//    public String id;
//
//    public String title;
//    public String developer;
//    public String genre;
//
//    public Integer year;
//    public List<String> platforms;
//    public List<String> tags;
//    public String description;
//    public String country;
//
//    @DynamoDBHashKey(attributeName = "id")
//    public String getGameId() {
//        return id;}
//
//    @DynamoDBAttribute(attributeName = "title")
//    public String getGameTitle() {
//        return title;
//    }
//
//    @DynamoDBAttribute(attributeName = "developer")
//    public String getDeveloper() {
//        return developer;
//    }
//
//    @DynamoDBAttribute(attributeName = "genre")
//    public String getGenre() {
//        return genre;
//    }
//
//    @DynamoDBAttribute(attributeName = "year")
//    public Integer getYear() { return year; }
//
//    @DynamoDBAttribute(attributeName = "platforms")
//    public List<String> getPlatforms() {
//        return platforms;
//    }
//
//    @DynamoDBAttribute(attributeName = "tags")
//    public List<String> getTags() {
//        return tags;
//    }
//
//    @DynamoDBAttribute(attributeName = "description")
//    public String getGameDescription(){
//        return description;
//    }
//
//    @DynamoDBAttribute(attributeName = "country")
//    public String getCountry() { return country; }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setDeveloper(String developer) {
//        this.developer = developer;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public void setCountry(String country) { this.country = country; }
//
//    public void setId(String id) { this.id = id;}
//
//    public void setPlatforms(List<String> platforms) {
//        this.platforms = platforms;
//    }
//
//    public void setTags(List<String> tags) {
//        this.tags = tags;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setYear(Integer year) { this.year = year; }
//
//     @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        VideoGameCatalogRecord that = (VideoGameCatalogRecord) o;
//        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(developer, that.developer) && Objects.equals(genre, that.genre) && Objects.equals(year, that.year) && Objects.equals(platforms, that.platforms) && Objects.equals(tags, that.tags) && Objects.equals(description, that.description) && Objects.equals(country, that.country);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, title, developer, genre, year, platforms, tags, description, country);
//    }
//}
//
>>>>>>> 230d7be (Completely recreated all classes that have to do with the Video Game Catalog. Kept original classes but COMMENTED THEM OUT SO THEY DON'T AFFECT CODE: VideoGameCreateRequest -> CatalogCreateRequest, VideoGameResponse -> CatalogResponse, VideoGameUpdateRequest -> CatalogUpdateRequest, CatalogControllerOriginal -> CatalogController, VideoGameCatalogRepository -> CatalogRepository, VideoGame -> Game , VideoGameCatalogService -> CatalogService. Commented out ALL tests to run app. Successfully able to Post, Get, Put, and Delete.)
