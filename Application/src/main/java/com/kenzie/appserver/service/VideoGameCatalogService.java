package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
import com.kenzie.appserver.service.model.GameService;

import org.springframework.stereotype.Service;

@Service
public class VideoGameCatalogService {
    private VideoGameCatalogRepository videoGameCatalogRepository;
    public VideoGameCatalogService(VideoGameCatalogRepository videoGameCatalogRepository){
        this.videoGameCatalogRepository = videoGameCatalogRepository;
    }
    //TODO** Have not made the findByName in the controller just yet**//
//    public GameService findByName(String name){
//        GameService gameFromService = catalogRepository
//                .findByName(id)
//                .map(game -> new GameService(game.getGameName()))
//                .orElse(null);
//
//        return gameFromService;
//    }
//    public GameService addNewGame(GameService game){
//        VideoGameCatalogRecord videoGameCatalogRecord = new VideoGameCatalogRecord();
//        videoGameCatalogRecord.setGameId(game.getGameId());
//        videoGameCatalogRecord.setGameName(game.getGameName());
//        videoGameCatalogRecord.setGameDescription(game.getGameDescription());
//        return game;
//    }
}

