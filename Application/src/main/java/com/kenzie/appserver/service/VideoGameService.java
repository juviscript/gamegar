package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.CatalogRecord;
import com.kenzie.appserver.repositories.CatalogRepository;
import com.kenzie.appserver.service.model.Game;

import org.springframework.stereotype.Service;

@Service
public class VideoGameService {
    private CatalogRepository catalogRepository;
    public VideoGameService(CatalogRepository catalogRepository){
        this.catalogRepository = catalogRepository;
    }
    public Game findById(String id){
        Game gameFromService = catalogRepository
                .findById(id)
                .map(game -> new Game(game.getGameId(), game.getGameId(),game.getGameDescription()))
                .orElse(null);

        return gameFromService;
    }
    public Game addNewGame(Game game){
        CatalogRecord catalogRecord = new CatalogRecord();
        catalogRecord.setGameId(game.getGameId());
        catalogRecord.setGameName(game.getGameName());
        catalogRecord.setGameDescription(game.getGameDescription());
        return game;
    }
}

