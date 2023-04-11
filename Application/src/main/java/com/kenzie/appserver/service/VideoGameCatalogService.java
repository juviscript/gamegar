
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
import java.util.stream.Collectors;
import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
//import com.kenzie.appserver.service.model.GameService;

import org.springframework.stereotype.Service;



//    FIXME: If we change the partition key from 'title' to 'ID' will need to change methods to match.
//           These methods are used in the CatalogController class.
//           Please update accordingly.

// Do we want to be able to find the games with the title, id, genre, etc? *****************

@Service
public class VideoGameCatalogService {
    private final VideoGameCatalogRepository videoGameCatalogRepository;
    public CacheStore cache;

    @Autowired
    public VideoGameCatalogService(VideoGameCatalogRepository videoGameCatalogRepository, CacheStore cache) {
        this.videoGameCatalogRepository = videoGameCatalogRepository;
        this.cache = cache;
    }

    public VideoGame findGameById(String id) {
        return videoGameCatalogRepository
        VideoGame cachedVideoGame = cache.get(id);
        if (cachedVideoGame != null) {
            return cachedVideoGame;
        }

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
        if (gameFromService != null) {
            cache.add(gameFromService.getId(), gameFromService);
        }

        return gameFromService;
    }

    public VideoGame findGameByTitle(String title) {

        return videoGameCatalogRepository
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

        // We can choose to create a cache so that the items get stored and accessed easier.
        // I don't think that was a requirement of the project, but if you guys want to!

    }
    public VideoGame findGameByDeveloper (String developer) {

        return videoGameCatalogRepository
                .findById(developer)
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
    }
    public VideoGame findGameByGenre (String genre) {

        return videoGameCatalogRepository
                .findById(genre)
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
    }
    public VideoGame findGameByTag (String tags) {

        return videoGameCatalogRepository
                .findById(tags)
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
    }
    public VideoGame findGameByCountry (String country) {

        return videoGameCatalogRepository
                .findById(country)
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
    }
    public VideoGame findGamesByYear (String year) {

        return videoGameCatalogRepository
                .findById(year)
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
    }
    public VideoGame findGameByPlatforms (String platforms) {

        return videoGameCatalogRepository
                .findById(platforms)
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
    }

    public VideoGame addNewGame(VideoGame game) {
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

        if (videoGameCatalogRecord != null) {
            cache.add(videoGameCatalogRecord.getGameId(), game);
        }

        return game;
    }

    public List<VideoGame> findAllGames() {
        List<VideoGame> games = new ArrayList<>();

        Iterable<VideoGameCatalogRecord> gameIterator = videoGameCatalogRepository.findAll();
        for (VideoGameCatalogRecord record : gameIterator) {
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
        if (gameFromCache != null) {
            cache.evict(gameFromCache.getId());
        }
    }

    public void deleteGameById(String gameId) {
//        VideoGame game = null;

        if (gameId != null) {
//            game = cache.get(gameId);
            videoGameCatalogRepository.deleteById(gameId);
            cache.evict(gameId);
        } else {
            throw new IllegalArgumentException("Video Game ID not valid.");
        }
    }
}
//        if (game != null) {



//package com.kenzie.appserver.service;

//
//
//import com.kenzie.appserver.config.CacheStore;
//
//
//import com.kenzie.appserver.config.CacheStore;
//
//import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
//import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
//import com.kenzie.appserver.service.model.VideoGame;
//import com.kenzie.appserver.service.model.Game;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.servlet.ThemeResolver;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
//import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
////import com.kenzie.appserver.service.model.GameService;
//
//import org.springframework.stereotype.Service;
//
//
//
////    FIXME: If we change the partition key from 'title' to 'ID' will need to change methods to match.
////           These methods are used in the CatalogController class.
////           Please update accordingly.
//
//// Do we want to be able to find the games with the title, id, genre, etc? *****************
//
//@Service
//public class VideoGameCatalogService {
//    private final VideoGameCatalogRepository videoGameCatalogRepository;
//    public CacheStore cache;
//
//    @Autowired
//    public VideoGameCatalogService(VideoGameCatalogRepository videoGameCatalogRepository, CacheStore cache) {
//        this.videoGameCatalogRepository = videoGameCatalogRepository;
//        this.cache = cache;
//    }
//
//    public VideoGame findGameById (String id) {
//

//        VideoGame cachedVideoGame = cache.get(id);
//        if (cachedVideoGame != null) {
//            return cachedVideoGame;
//        }


//
//        VideoGame gameFromService = videoGameCatalogRepository
//        return videoGameCatalogRepository

//                .findById(id)
//                .map(game -> new VideoGame(game.getGameId(),
//                        game.getGameTitle(),
//                        game.getDeveloper(),
//                        game.getGenre(),
//                        game.getYear(),
//                        game.getPlatforms(),
//                        game.getTags(),
//                        game.getGameDescription(),
//                        game.getCountry()))
//                .orElse(null);
//    }
//
//    public VideoGame findGameByTitle (String title) {
//
//        return videoGameCatalogRepository
//                .findById(title)
//                .map(game -> new VideoGame(game.getGameTitle(),
//                        game.getGameId(),
//                        game.getDeveloper(),
//                        game.getGenre(),
//                        game.getYear(),
//                        game.getPlatforms(),
//                        game.getTags(),
//                        game.getGameDescription(),
//                        game.getCountry()))
//                .orElse(null);
//
//        // We can choose to create a cache so that the items get stored and accessed easier.
//        // I don't think that was a requirement of the project, but if you guys want to!
//
//    }
//    public VideoGame findGameByDeveloper (String developer) {
//
//        return videoGameCatalogRepository
//                .findById(developer)
//                .map(game -> new VideoGame(game.getGameTitle(),
//                        game.getGameId(),
//                        game.getDeveloper(),
//                        game.getGenre(),
//                        game.getYear(),
//                        game.getPlatforms(),
//                        game.getTags(),
//                        game.getGameDescription(),
//                        game.getCountry()))
//                .orElse(null);
//    }
//    public VideoGame findGameByGenre (String genre) {
//
//        return videoGameCatalogRepository
//                .findById(genre)
//                .map(game -> new VideoGame(game.getGameTitle(),
//                        game.getGameId(),
//                        game.getDeveloper(),
//                        game.getGenre(),
//                        game.getYear(),
//                        game.getPlatforms(),
//                        game.getTags(),
//                        game.getGameDescription(),
//                        game.getCountry()))
//                .orElse(null);
//    }
//    public VideoGame findGameByTag (String tags) {
//
//        return videoGameCatalogRepository
//                .findById(tags)
//                .map(game -> new VideoGame(game.getGameTitle(),
//                        game.getGameId(),
//                        game.getDeveloper(),
//                        game.getGenre(),
//                        game.getYear(),
//                        game.getPlatforms(),
//                        game.getTags(),
//                        game.getGameDescription(),
//                        game.getCountry()))
//                .orElse(null);
//    }
//    public VideoGame findGameByCountry (String country) {
//
//        return videoGameCatalogRepository
//                .findById(country)
//                .map(game -> new VideoGame(game.getGameTitle(),
//                        game.getGameId(),
//                        game.getDeveloper(),
//                        game.getGenre(),
//                        game.getYear(),
//                        game.getPlatforms(),
//                        game.getTags(),
//                        game.getGameDescription(),
//                        game.getCountry()))
//                .orElse(null);
//    }
//    public VideoGame findGamesByYear (String year) {
//
//        return videoGameCatalogRepository
//                .findById(year)
//                .map(game -> new VideoGame(game.getGameTitle(),
//                        game.getGameId(),
//                        game.getDeveloper(),
//                        game.getGenre(),
//                        game.getYear(),
//                        game.getPlatforms(),
//                        game.getTags(),
//                        game.getGameDescription(),
//                        game.getCountry()))
//                .orElse(null);
//    }
//    public VideoGame findGameByPlatforms (String platforms) {
//
//        return videoGameCatalogRepository
//                .findById(platforms)
//                .map(game -> new VideoGame(game.getGameTitle(),
//                        game.getGameId(),
//                        game.getDeveloper(),
//                        game.getGenre(),
//                        game.getYear(),
//                        game.getPlatforms(),
//                        game.getTags(),
//                        game.getGameDescription(),
//                        game.getCountry()))
//                .orElse(null);
//    }
//
//    public VideoGame addNewGame(VideoGame game){
//        VideoGameCatalogRecord videoGameCatalogRecord = new VideoGameCatalogRecord();
//
//        //Do we want the users to create an id for the video game?****
//        videoGameCatalogRecord.setId(game.getId());
//        videoGameCatalogRecord.setTitle(game.getGameTitle());
//        videoGameCatalogRecord.setDeveloper(game.getDeveloper());
//        videoGameCatalogRecord.setGenre(game.getGenre());
//        videoGameCatalogRecord.setYear(game.getYear()); //added year
//        videoGameCatalogRecord.setTags(game.getTags()); //added tags, is that ok?
//        videoGameCatalogRecord.setDescription(game.getDescription());
//        videoGameCatalogRecord.setYear(game.getYear()); //added year
//        videoGameCatalogRepository.save(videoGameCatalogRecord);
//        return game;
//    }
//
//
//    public List<VideoGame> findAllGames() {
//        List<VideoGame> games = new ArrayList<>();
//
//        Iterable<VideoGameCatalogRecord> gameIterator = videoGameCatalogRepository.findAll();
//        for(VideoGameCatalogRecord record : gameIterator) {
//            games.add(new VideoGame(record.getGameId(),
//                    record.getGameTitle(),
//                    record.getDeveloper(),
//                    record.getGenre(),
//                    record.getYear(),
//                    record.getPlatforms(),
//                    record.getTags(),
//                    record.getGameDescription(),
//                    record.getCountry()));
//        }
//        return games;
//    }
//
//    public void updateGame(VideoGame game) {
//        if (videoGameCatalogRepository.existsById(game.getId())) {
//            VideoGameCatalogRecord gameRecord = new VideoGameCatalogRecord();
//            gameRecord.setId(game.getId());
//            gameRecord.setTitle(game.getGameTitle());
//            gameRecord.setDeveloper(game.getDeveloper());
//            gameRecord.setGenre(game.getGenre());
//            gameRecord.setYear(game.getYear());
//            gameRecord.setPlatforms(game.getPlatforms());
//            gameRecord.setDescription(game.getDescription());
//            gameRecord.setCountry(game.getCountry());
//            videoGameCatalogRepository.save(gameRecord);
//        }
//        VideoGame gameFromCache = null;
//        gameFromCache = cache.get(game.getId());
//        if(gameFromCache != null){
//            cache.evict(gameFromCache.getId());
//        }
//    }
//
//        public void deleteGameById(String gameId){
//            videoGameCatalogRepository.deleteById(gameId);
//            Game game = null;
//            if (gameId != null) {
//                game = cache.get(gameId);
//            } else {
//                throw new IllegalArgumentException("Video Game ID not valid.");
//            }
//            if (game != null) {
//                cache.evict(game.getId());
//            }
//        }
//
//}
