package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
import com.kenzie.appserver.service.model.VideoGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class VideoGameCatalogServiceTest {

    private VideoGameCatalogRepository videoGameCatalogRepository;
    private CacheStore cacheStore;
    private VideoGameCatalogService videoGameCatalogService;

    @BeforeEach
    void setup() {
        videoGameCatalogRepository = mock(VideoGameCatalogRepository.class);
        cacheStore = mock(CacheStore.class);
        videoGameCatalogService = new VideoGameCatalogService(videoGameCatalogRepository, cacheStore);
    }

    @Test
    void deleteGame_null_input() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                videoGameCatalogService.deleteGameById(null));
    }

    @Test
    void deleteGame() {
        String gameId = "1";
        VideoGameCatalogRecord record = new VideoGameCatalogRecord();
        record.setId(gameId);

        Mockito.when(videoGameCatalogRepository.existsById(anyString())).thenReturn(true);
        Mockito.when(videoGameCatalogRepository.findById(anyString())).thenReturn(Optional.of(record));

        when(cacheStore.get(record.getGameId())).thenReturn(new VideoGame(gameId, "name", "1",
                "action", 2007, new LinkedList<>(), new LinkedList<>(), "cute game", "US"));

        videoGameCatalogService.deleteGameById(gameId);

        verify(cacheStore).evict(record.getGameId());

        verify(videoGameCatalogRepository, times(1)).deleteById(gameId);
    }

    @Test
    void videoGameCatalogService_findByGameId_idIsNull() {
        // GIVEN
        VideoGameCatalogRecord nullIdGame = new VideoGameCatalogRecord();
        nullIdGame.setId(null);

        // WHEN
        when(videoGameCatalogRepository.findById(nullIdGame.getGameId())).thenReturn(Optional.empty());
        VideoGame response = videoGameCatalogService.findGameById(nullIdGame.getGameId());

        //THEN
        Assertions.assertNull(response);
    }

    @Test
    void findByGameId() {
        // GIVEN
        String gameId = randomUUID().toString();

        VideoGameCatalogRecord record = new VideoGameCatalogRecord();
        record.setId(gameId);
        record.setTitle("gametitle");
        record.setYear(2007);
        record.setDeveloper("bobby");
        record.setDescription("great game");
        record.setGenre("action");
        record.setPlatforms(new LinkedList<>());
        record.setTags(new LinkedList<>());
        record.setCountry("us");

        when(videoGameCatalogRepository.findById(gameId)).thenReturn(Optional.of(record));
        // WHEN
        VideoGame videoGame = videoGameCatalogService.findGameById(gameId);

        // THEN
        Assertions.assertNotNull(videoGame, "The video game is returned");
        assertEquals(record.getGameId(), videoGame.getId(), "The video game id matches");
        assertEquals(record.getGameTitle(), videoGame.getGameTitle(), "The video game title matches");
        assertEquals(record.getYear(), videoGame.getYear(), "The video game year matches");
        assertEquals(record.getDeveloper(), videoGame.getDeveloper(), "The video game developer matches");
        assertEquals(record.getGameDescription(), videoGame.getDescription(), "The video game description matches");
        assertEquals(record.getGenre(), videoGame.getGenre(), "The video game genre matches");
        assertEquals(record.getPlatforms(), videoGame.getPlatforms(), "The video game platform matches");
        assertEquals(record.getTags(), videoGame.getTags(), "The video game tags match");
        assertEquals(record.getCountry(), videoGame.getCountry(), "The video game country matches");
    }

}
