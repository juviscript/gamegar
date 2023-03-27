package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.model.CatalogRecord;
import com.kenzie.appserver.repositories.CatalogRepository;
import com.kenzie.appserver.service.model.Game;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class VideoGameService {
    private CatalogRepository catalogRepository;
    private CacheStore cache;
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
    public List<Game> findAllGames(){
        List<Game> games = new ArrayList<>();

        Iterable<CatalogRecord> records = catalogRepository.findAll();
        for(CatalogRecord record : records){
            games.add(new Game(record.getGameId(),
                    record.getGameName(),
                    record.getGameDescription()));
        }
        return games;
    }
    public Game findByGameId(String gameId){
        Game cachedGame = cache.get(gameId);
        if(cachedGame != null){
            return cachedGame;
        }
        Game gamesFromBackend = catalogRepository.findById(gameId)
                .map(game -> new Game(game.getGameId(),
                        game.getGameName(),
                        game.getGameDescription()))
                .orElse(null);
        if(gamesFromBackend != null){
            cache.add(gamesFromBackend.getGameId(), gamesFromBackend);
        }
        return gamesFromBackend;
    }
}

