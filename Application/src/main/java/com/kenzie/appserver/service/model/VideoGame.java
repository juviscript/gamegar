package com.kenzie.appserver.service.model;

import java.util.LinkedList;
import java.util.UUID;

public class VideoGame {

    public String title;
    public String developer;
    public String genre;
    public static UUID id = UUID.randomUUID();       // Create a randomly generated ID.
    public String console;
    public LinkedList<String> tags;
    public double price;                            // Should we keep price on there as something to display on the page?
    public String description;

    public VideoGame(String title, String developer, String genre) {
        this.title = title;
        this.developer = developer;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public static UUID getId() {
        return id;
    }

    public static void setId(UUID id) {
        VideoGame.id = id;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public LinkedList<String> getTags() {
        return tags;
    }

    public void setTags(LinkedList<String> tags) {
        this.tags = tags;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "VideoGame{" +
                "title='" + title + '\'' +
                ", developer='" + developer + '\'' +
                ", genre='" + genre + '\'' +
                ", id=" + id +
                ", console='" + console + '\'' +
                ", tags=" + tags +
                ", price=" + price +
                '}';
    }


}
