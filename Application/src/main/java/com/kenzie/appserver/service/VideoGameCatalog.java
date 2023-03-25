package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.CatalogRecord;
import com.kenzie.appserver.repositories.CatalogRepository;
import com.kenzie.appserver.service.model.GameService;

import org.springframework.stereotype.Service;

@Service
public class VideoGameCatalog {
    private CatalogRepository catalogRepository;
    public VideoGameCatalog(CatalogRepository catalogRepository){
        this.catalogRepository = catalogRepository;
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
    public GameService addNewGame(GameService game){
        CatalogRecord catalogRecord = new CatalogRecord();
        catalogRecord.setGameId(game.getGameId());
        catalogRecord.setGameName(game.getGameName());
        catalogRecord.setGameDescription(game.getGameDescription());
        return game;
    }
}

