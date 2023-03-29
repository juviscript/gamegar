package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.VideoGameCatalogRepository;

import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
import com.kenzie.appserver.service.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ThemeResolver;


//    FIXME: If we change the partition key from 'title' to 'ID' will need to change methods to match.
//           These methods are used in the CatalogController class.
//           Please update accordingly.

@Service
public class VideoGameCatalogService {
    private final VideoGameCatalogRepository videoGameCatalogRepository;

    @Autowired
   public VideoGameCatalogService(VideoGameCatalogRepository videoGameCatalogRepository) {
        this.videoGameCatalogRepository = videoGameCatalogRepository;
    }

    public VideoGame findGameByTitle (String title) {

        VideoGame gameFromService = videoGameCatalogRepository
                .findById(title)
                .map(game -> new VideoGame(game.getGameTitle(),
                        game.getDeveloper(),
                        game.getGenre(),
                        game.getGameId()))
                .orElse(null);

        return gameFromService;

        // We can choose to create a cache so that the items get stored and accessed easier.
        // I don't think that was a requirement of the project, but if you guys want to!

    }

    public VideoGame addNewGame(VideoGame game){
        VideoGameCatalogRecord videoGameCatalogRecord = new VideoGameCatalogRecord();

        videoGameCatalogRecord.setTitle(game.getGameTitle());
        videoGameCatalogRecord.setDeveloper(game.getDeveloper());
        videoGameCatalogRecord.setGenre(game.getGenre());
        videoGameCatalogRecord.setDescription(game.getDescription());
        videoGameCatalogRepository.save(videoGameCatalogRecord);                  // Help :(
        return game;
    }

//        TODO: Create these methods:
//                getAllGames()
//                updateGame()
//                deleteGameById() or deleteGameByTitle()

}

