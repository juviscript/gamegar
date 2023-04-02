package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;

import com.kenzie.appserver.service.model.VideoGame;
import org.springframework.stereotype.Service;

@Service
public class VideoGameService {
    private VideoGameCatalogRepository catalogRepository;
    public VideoGameService(VideoGameCatalogRepository catalogRepository){
        this.catalogRepository = catalogRepository;
    }
    public VideoGame findById(String id){
        VideoGame gameFromService = catalogRepository
                .findById(id)
                .map(game -> new VideoGame(game.getGameId(), game.getGameTitle(), game.getDeveloper(),
                        game.getGenre(),game.getYear(), game.getPlatforms(), game.getTags(), game.getGameDescription(),
                        game.getCountry()))
                .orElse(null);

        return gameFromService;
    }
    public VideoGame addNewGame(VideoGame game){
        VideoGameCatalogRecord catalogRecord = new VideoGameCatalogRecord();
        catalogRecord.setId(game.getId());
        catalogRecord.setTitle(game.getGameTitle());
        catalogRecord.setDeveloper(game.getDeveloper());
        catalogRecord.setGenre(game.getGenre());
        catalogRecord.setYear(game.getYear());
        catalogRecord.setPlatforms(game.getPlatforms());
        catalogRecord.setTags(game.getTags());
        catalogRecord.setDescription(game.getDescription());
        catalogRecord.setCountry(game.getCountry());
        return game;
    }
}

