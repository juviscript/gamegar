package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.VideoGameCatalogRepository;

import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
import com.kenzie.appserver.service.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoGameCatalogService {
    private final VideoGameCatalogRepository videoGameCatalogRepository;

    @Autowired
   public VideoGameCatalogService(VideoGameCatalogRepository videoGameCatalogRepository) {
        this.videoGameCatalogRepository = videoGameCatalogRepository;
    }

    //TODO** Have not made the findByName in the controller just yet**//

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
        videoGameCatalogRepository.save(game);                  // Help :(
        return game;
    }
}

