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

<<<<<<< Updated upstream
    @DynamoDBHashKey(attributeName = "id")
    public String getGameId() {
        return id;}

    @DynamoDBAttribute(attributeName = "title")
=======
    @DynamoDBHashKey(attributeName = "title")

>>>>>>> Stashed changes
    public String getGameTitle() {
        return title;
    }

<<<<<<< Updated upstream
    @DynamoDBAttribute(attributeName = "developer")
    public String getDeveloper() {
        return developer;
    }
=======
        @DynamoDBRangeKey(attributeName = "id")
        public UUID getGameId () {
            return id;
        }

        @DynamoDBAttribute(attributeName = "developer")
        public String getDeveloper () {
            return developer;
        }
>>>>>>> Stashed changes

        @DynamoDBAttribute(attributeName = "genre")
        public String getGenre () {
            return genre;
        }

<<<<<<< Updated upstream
    @DynamoDBAttribute(attributeName = "year")
    public Integer getYear() { return year; }

    @DynamoDBAttribute(attributeName = "platforms")
    public LinkedList<String> getPlatforms() {
        return platforms;
    }
=======
        @DynamoDBAttribute(attributeName = "platforms")
        public LinkedList<String> getPlatforms () {
            return platforms;
        }
>>>>>>> Stashed changes

        @DynamoDBAttribute(attributeName = "tags")
        public LinkedList<String> getTags () {
            return tags;
        }

        @DynamoDBAttribute(attributeName = "description")
        public String getGameDescription () {
            return description;
        }

<<<<<<< Updated upstream
    @DynamoDBAttribute(attributeName = "country")
    public String getCountry() { return country; }

    public void setTitle(String title) {
        this.title = title;
    }
=======
        public void setTitle (String title){
            this.title = title;
        }
>>>>>>> Stashed changes

        public void setDeveloper (String developer){
            this.developer = developer;
        }

        public void setGenre (String genre){
            this.genre = genre;
        }

<<<<<<< Updated upstream
    public void setCountry(String country) { this.country = country; }

    public void setId(String id) { this.id = id;}
=======
            public static void setId (UUID id){
                UUID random = UUID.randomUUID();
                random = id;
            }

            public void setPlatforms (LinkedList < String > platforms) {
                this.platforms = platforms;
            }
>>>>>>> Stashed changes

            public void setTags (LinkedList < String > tags) {
                this.tags = tags;
            }

            public void setDescription (String description){
                this.description = description;
            }

            @Override
            public boolean equals (Object o){
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                VideoGameCatalogRecord that = (VideoGameCatalogRecord) o;
                return title.equals(that.title) && developer.equals(that.developer) && Objects.equals(genre, that.genre) && Objects.equals(platforms, that.platforms) && Objects.equals(tags, that.tags) && Objects.equals(description, that.description);
            }

<<<<<<< Updated upstream
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
            @Override
            public int hashCode () {
                return Objects.hash(title, developer, genre, platforms, tags, description);
            }
        }

>>>>>>> Stashed changes
