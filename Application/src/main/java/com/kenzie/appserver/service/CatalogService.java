package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.CatalogRepository;
import com.kenzie.appserver.repositories.model.CatalogRecord;
import com.kenzie.appserver.service.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {
    private com.kenzie.appserver.repositories.CatalogRepository catalogRepository;
    private CacheStore cache;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository, CacheStore cache) {
        this.catalogRepository = catalogRepository;
        this.cache = cache;
    }

    public List<Game> findAllGames() {

        List<Game> games = new ArrayList<>();

        Iterable<CatalogRecord> gameIterator = catalogRepository.findAll();

        for(CatalogRecord record : gameIterator) {
            games.add(new Game(record.getId(),
                    record.getTitle(),
                    record.getDeveloper(),
                    record.getGenre(),
                    record.getYear(),
                    record.getDescription(),
                    record.getCountry(),
                    record.getPlatforms(),
                    record.getTags(),
                    record.getImage()));
        }

        return games;

    }

    public Game findByGameId(String id) {
        Game cachedGame = cache.get(id);

        // Check if Game is cached and return it if true
        if (cachedGame != null) {
            return cachedGame;
        }

        // if not cached, find the Game
        Game gameFromBackendService = catalogRepository

                .findById(id)
                .map(game -> new Game(game.getId(),
                        game.getTitle(),
                        game.getDeveloper(),
                        game.getGenre(),
                        game.getYear(),
                        game.getDescription(),
                        game.getCountry(),
                        game.getPlatforms(),
                        game.getTags(),
                        game.getImage()))
                .orElse(null);

        // if Game found, cache it
        if (gameFromBackendService != null) {
            cache.add(gameFromBackendService.getId(), gameFromBackendService);
        }
        // return Game
        return gameFromBackendService;

    }

    public Game findGameByTitle (String title) {
        Game gameTitle = catalogRepository
                .findByTitle(title)
                .map(game -> new Game(game.getId(),
                        game.getTitle(),
                        game.getDeveloper(),
                        game.getGenre(),
                        game.getYear(),
                        game.getDescription(),
                        game.getCountry(),
                        game.getPlatforms(),
                        game.getTags(),
                        game.getImage()))
                .orElse(null);

        if (gameTitle != null) {
            cache.add(gameTitle.getId(), gameTitle);
        }

        return gameTitle;
    }

    public Game addNewGame(Game game) {

        CatalogRecord catalogRecord = new CatalogRecord();

        catalogRecord.setId(game.getId());
        catalogRecord.setTitle(game.getTitle());
        catalogRecord.setDeveloper(game.getDeveloper());
        catalogRecord.setGenre(game.getGenre());
        catalogRecord.setYear(game.getYear());
        catalogRecord.setDescription(game.getDescription());
        catalogRecord.setCountry(game.getCountry());
        catalogRecord.setPlatforms(game.getPlatforms());
        catalogRecord.setTags(game.getTags());
        catalogRecord.setImage(game.getImage());
        catalogRepository.save(catalogRecord);

        return game;
    }

    public void updateGame(Game game) {

        if (catalogRepository.existsById(game.getId())) {
            CatalogRecord catalogRecord = new CatalogRecord();

            catalogRecord.setId(game.getId());
            catalogRecord.setTitle(game.getTitle());
            catalogRecord.setDeveloper(game.getDeveloper());
            catalogRecord.setGenre(game.getGenre());
            catalogRecord.setYear(game.getYear());
            catalogRecord.setDescription(game.getDescription());
            catalogRecord.setCountry(game.getCountry());
            catalogRecord.setPlatforms(game.getPlatforms());
            catalogRecord.setTags(game.getTags());
            catalogRecord.setImage(game.getImage());
            catalogRepository.save(catalogRecord);

            cache.evict(game.getId());
        }
    }

    public void deleteGame(String id) {

        catalogRepository.deleteById(id);
        cache.evict(id);
    }



}
