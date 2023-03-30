package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.VideoGameCatalogRepository;

import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
import com.kenzie.appserver.service.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ThemeResolver;

import java.util.ArrayList;
import java.util.List;


//    FIXME: If we change the partition key from 'title' to 'ID' will need to change methods to match.
//           These methods are used in the CatalogController class.
//           Please update accordingly.

// Do we want to be able to find the games with the title, id, genre, etc? *****************

@Service
public class VideoGameCatalogService {
    private final VideoGameCatalogRepository videoGameCatalogRepository;
    private CacheStore cache;

    @Autowired
    public VideoGameCatalogService(VideoGameCatalogRepository videoGameCatalogRepository, CacheStore cache) {
        this.videoGameCatalogRepository = videoGameCatalogRepository;
        this.cache = cache;
    }

    public VideoGame findGameById (String id) {

        VideoGame gameFromService = videoGameCatalogRepository
                .findById(id)
                .map(game -> new VideoGame(game.getGameId(),
                        game.getGameTitle(),
                        game.getDeveloper(),
                        game.getGenre(),
                        game.getYear(),
                        game.getPlatforms(),
                        game.getTags(),
                        game.getGameDescription(),
                        game.getCountry()))
                .orElse(null);

        return gameFromService;
    }

    public VideoGame findGameByTitle (String title) {

        VideoGame gameFromService = videoGameCatalogRepository
                .findById(title)
                .map(game -> new VideoGame(game.getGameTitle(),
                        game.getGameId(),
                        game.getDeveloper(),
                        game.getGenre(),
                        game.getYear(),
                        game.getPlatforms(),
                        game.getTags(),
                        game.getGameDescription(),
                        game.getCountry()))
                .orElse(null);

        return gameFromService;

        // We can choose to create a cache so that the items get stored and accessed easier.
        // I don't think that was a requirement of the project, but if you guys want to!

    }

    public VideoGame addNewGame(VideoGame game){
        VideoGameCatalogRecord videoGameCatalogRecord = new VideoGameCatalogRecord();

        //Do we want the users to create an id for the video game?****
        videoGameCatalogRecord.setId(game.getId());
        videoGameCatalogRecord.setTitle(game.getGameTitle());
        videoGameCatalogRecord.setDeveloper(game.getDeveloper());
        videoGameCatalogRecord.setGenre(game.getGenre());
        videoGameCatalogRecord.setYear(game.getYear()); //added year
        videoGameCatalogRecord.setTags(game.getTags()); //added tags, is that ok?
        videoGameCatalogRecord.setDescription(game.getDescription());
        videoGameCatalogRecord.setYear(game.getYear()); //added year
        videoGameCatalogRepository.save(videoGameCatalogRecord);
        return game;
    }

    public List<VideoGame> findAllGames() {
        List<VideoGame> games = new ArrayList<>();

        Iterable<VideoGameCatalogRecord> gameIterator = videoGameCatalogRepository.findAll();
        for(VideoGameCatalogRecord record : gameIterator) {
            games.add(new VideoGame(record.getGameId(),
                    record.getGameTitle(),
                    record.getDeveloper(),
                    record.getGenre(),
                    record.getYear(),
                    record.getPlatforms(),
                    record.getTags(),
                    record.getGameDescription(),
                    record.getCountry()));
        }
        return games;
    }

    public void updateGame(VideoGame game) {
        if (videoGameCatalogRepository.existsById(game.getId())) {
            VideoGameCatalogRecord gameRecord = new VideoGameCatalogRecord();
            gameRecord.setId(game.getId());
            gameRecord.setTitle(game.getGameTitle());
            gameRecord.setDeveloper(game.getDeveloper());
            gameRecord.setGenre(game.getGenre());
            gameRecord.setYear(game.getYear());
            gameRecord.setPlatforms(game.getPlatforms());
            gameRecord.setDescription(game.getDescription());
            gameRecord.setCountry(game.getCountry());
            videoGameCatalogRepository.save(gameRecord);
        }
        VideoGame gameFromCache = null;
        gameFromCache = cache.get(game.getId());
        if(gameFromCache != null){
            cache.evict(gameFromCache.getId());
        }
    }

        public void deleteGameById(String gameId){
            videoGameCatalogRepository.deleteById(gameId);
            VideoGame game = null;
            if (gameId != null) {
                game = cache.get(gameId);
            } else {
                throw new IllegalArgumentException("Video Game ID not valid.");
            }
            if (game != null) {
                cache.evict(game.getId());
            }
        }

}
