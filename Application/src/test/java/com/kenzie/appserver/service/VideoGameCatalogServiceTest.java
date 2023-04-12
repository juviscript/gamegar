package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.CatalogRepository;
import com.kenzie.appserver.repositories.model.CatalogRecord;
import com.kenzie.appserver.service.model.Game;
import com.kenzie.appserver.service.model.User;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class VideoGameCatalogServiceTest {

    private CatalogRepository catalogRepository;
    private CacheStore cacheStore;
    private CatalogService catalogService;
    private final MockNeat mockNeat = MockNeat.threadLocal();

    @BeforeEach
    void setup() {
        catalogRepository = mock(CatalogRepository.class);
        cacheStore = mock(CacheStore.class);
        catalogService = new CatalogService(catalogRepository, cacheStore);
    }

//    @Test
//    void deleteGame_null_input() {
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                catalogService.deleteGame(null));
//    }

    @Test
    void deleteGame() {
        String gameId = "1";
        List<String> test = new LinkedList<>();
        test.add("example");
        test.add("example2");

        CatalogRecord record = new CatalogRecord();
        record.setId(gameId);

        Mockito.when(catalogRepository.existsById(anyString())).thenReturn(true);
        Mockito.when(catalogRepository.findById(anyString())).thenReturn(Optional.of(record));

        when(cacheStore.get(record.getId())).thenReturn(new Game(gameId, "name", "1",
                "action", 2007, "cute game","US", "test", "test", "test"));

        catalogService.deleteGame(gameId);

        verify(cacheStore).evict(record.getId());

        verify(catalogRepository, times(1)).deleteById(gameId);
    }

    @Test
    void videoGameCatalogService_findByGameId_idIsNull() {
        // GIVEN
        CatalogRecord nullIdGame = new CatalogRecord();
        nullIdGame.setId(null);

        // WHEN
        when(catalogRepository.findById(nullIdGame.getId())).thenReturn(Optional.empty());
        Game response = catalogService.findByGameId(nullIdGame.getId());

        //THEN
        Assertions.assertNull(response);
    }

    @Test
    void findByGameId() {
        // GIVEN
        String gameId = randomUUID().toString();

        CatalogRecord record = new CatalogRecord();
        record.setId(gameId);
        record.setTitle("gametitle");
        record.setYear(2007);
        record.setDeveloper("bobby");
        record.setDescription("great game");
        record.setGenre("action");
        record.setPlatforms("platforms");
        record.setTags("tags");
        record.setCountry("US");
        record.setImage("image");

        when(catalogRepository.findById(gameId)).thenReturn(Optional.of(record));
        // WHEN
        Game videoGame = catalogService.findByGameId(gameId);

        // THEN
        Assertions.assertNotNull(videoGame, "The video game is returned");
        assertEquals(record.getId(), videoGame.getId(), "The video game id matches");
        assertEquals(record.getTitle(), videoGame.getTitle(), "The video game title matches");
        assertEquals(record.getDeveloper(), videoGame.getDeveloper(), "The video game developer matches");
        assertEquals(record.getGenre(), videoGame.getGenre(), "The video game genre matches");
        assertEquals(record.getYear(), videoGame.getYear(), "The video game year matches");
        assertEquals(record.getDescription(), videoGame.getDescription(), "The video game description matches");
        assertEquals(record.getCountry(), videoGame.getCountry(), "The video game country matches");
        assertEquals(record.getPlatforms(), videoGame.getPlatforms(), "The video game platform matches");
        assertEquals(record.getTags(), videoGame.getTags(), "The video game tags match");
        assertEquals(record.getImage(), videoGame.getImage(), "The video game image matches");
    }

    @Test
    void addNewGame() {
        // GIVEN
        String gameId = randomUUID().toString();

        Game videoGame = new Game(gameId, "Kirby", "Nintendo", "Adventure", 2022,
                "Kirby is super cute", "Japan","test", "test", "image");

        ArgumentCaptor<CatalogRecord> gameRecordCaptor = ArgumentCaptor.forClass(CatalogRecord.class);

        // WHEN
        Game returnedGame = catalogService.addNewGame(videoGame);

        // THEN
        Assertions.assertNotNull(returnedGame);

        verify(catalogRepository).save(gameRecordCaptor.capture());

        CatalogRecord record = gameRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The game record is returned");
        assertEquals(record.getId(), videoGame.getId(), "The game id matches");
        assertEquals(record.getTitle(), videoGame.getTitle(), "The video game title matches");
        assertEquals(record.getDeveloper(), videoGame.getDeveloper(), "The video game developer matches");
        assertEquals(record.getGenre(), videoGame.getGenre(), "The video game genre matches");
        assertEquals(record.getYear(), videoGame.getYear(), "The video game year matches");
        assertEquals(record.getDescription(), videoGame.getDescription(), "The video game description matches");
        assertEquals(record.getCountry(), videoGame.getCountry(), "The video game country matches");
        assertEquals(record.getPlatforms(), videoGame.getPlatforms(), "The video game platform matches");
        assertEquals(record.getTags(), videoGame.getTags(), "The video game tags match");
        assertEquals(record.getImage(), videoGame.getImage(), "The video game image matches");

    }

    @Test
    public void updateGame_validGame_gameUpdated() {

        String gameId = randomUUID().toString();

        Game videoGame = new Game(gameId, "gametitle", "developer", "genre", 2000,
                "description", "country", "test", "test", "image");

       CatalogRecord videoGameCatalogRecord = new CatalogRecord();
        videoGameCatalogRecord.setId(videoGame.getId());
        videoGameCatalogRecord.setTitle(videoGame.getTitle());
        videoGameCatalogRecord.setDeveloper(videoGame.getDeveloper());
        videoGameCatalogRecord.setGenre(videoGame.getGenre());
        videoGameCatalogRecord.setYear(videoGame.getYear());
        videoGameCatalogRecord.setDescription(videoGame.getDescription());
        videoGameCatalogRecord.setCountry(videoGame.getCountry());
        videoGameCatalogRecord.setPlatforms(videoGame.getPlatforms());
        videoGameCatalogRecord.setTags(videoGame.getTags());
        videoGameCatalogRecord.setImage(videoGame.getImage());


        when(catalogRepository.existsById(videoGame.getId())).thenReturn(true);

        ArgumentCaptor<CatalogRecord> argumentCaptor = ArgumentCaptor.forClass(CatalogRecord.class);
        when(catalogRepository.findById(videoGame.getId())).thenReturn(Optional.of(videoGameCatalogRecord));
        when(catalogRepository.save(argumentCaptor.capture())).thenReturn(videoGameCatalogRecord);
        when(cacheStore.get(videoGame.getId())).thenReturn(videoGame);

        catalogService.updateGame(videoGame);

        verify(cacheStore).evict(videoGame.getId());
        verify(catalogRepository).existsById(videoGame.getId());
        verify(catalogRepository).save(argumentCaptor.capture());
       CatalogRecord capturedGameRecord = argumentCaptor.getValue();
        assertEquals(capturedGameRecord.getId(), videoGameCatalogRecord.getId());
        assertEquals(capturedGameRecord.getTitle(), videoGameCatalogRecord.getTitle());
        assertEquals(capturedGameRecord.getDeveloper(), videoGameCatalogRecord.getDeveloper());
        assertEquals(capturedGameRecord.getGenre(), videoGameCatalogRecord.getGenre());
        assertEquals(capturedGameRecord.getYear(), videoGameCatalogRecord.getYear());
        assertEquals(capturedGameRecord.getDescription(), videoGameCatalogRecord.getDescription());
        assertEquals(capturedGameRecord.getCountry(), videoGameCatalogRecord.getCountry());
        assertEquals(capturedGameRecord.getPlatforms(), videoGameCatalogRecord.getPlatforms());
        assertEquals(capturedGameRecord.getTags(), videoGameCatalogRecord.getTags());
        assertEquals(capturedGameRecord.getImage(), videoGameCatalogRecord.getImage());

    }

    @Test
    public void updateGame_descriptionUpdated() {
        String id = randomUUID().toString();
        String description = mockNeat.strings().val();

        Game videoGame = new Game(id, "gametitle", "developer", "genre", 2000,
                description, "country", "test", "test", "image");

        when(cacheStore.get(videoGame.getDescription())).thenReturn(videoGame);

       catalogService.updateGame(videoGame);
    }

//     @Test
//    public void findGameByTitle_titleIsNull(){
//        CatalogRecord nullGame = new CatalogRecord();
//        nullGame.setTitle(null);
//
//        when(catalogRepository.findById(nullGame.getId())).thenReturn(Optional.empty());
//        Game response = catalogService.findGameByTitle(nullGame.getGameTitle());
//
//        Assertions.assertNull(response);
//    }
    @Test
    public void findAllGames(){
        CatalogRecord game1 = new CatalogRecord();
        game1.setId(randomUUID().toString());
        game1.setTitle("Destiny 2");
        game1.setDeveloper("Bungie");
        game1.setGenre("Action/Adventure");
        game1.setYear(2017);
        game1.setDescription("Big shooty shooty space adventure game");
        game1.setCountry("USA");
        game1.setPlatforms("platforms");
        game1.setTags("tags");
        game1.setImage("image");


       CatalogRecord game2 = new CatalogRecord();
        game2.setId(randomUUID().toString());
        game2.setTitle("The Legend of Zelda: Breath of the Wild");
        game2.setDeveloper("Nintendo");
        game2.setGenre("Action/Adventure");
        game2.setYear(2017);
        game2.setDescription("117 year old boy awakens to save princess from the evil clutches of Ganon");
        game2.setCountry("Japan");
        game2.setPlatforms("platforms");
        game2.setTags("tags");
        game2.setImage("image2");

        List<CatalogRecord> records = new ArrayList<>();

        records.add(game1);
        records.add(game2);

        when(catalogRepository.findAll()).thenReturn(records);

        List<Game> recordList = catalogService.findAllGames();

        Assertions.assertNotNull(recordList, "The list of games is returned");
        Assertions.assertEquals(2, recordList.size(), "Here are 2 games");

        for(Game videoGame : recordList) {
            if(videoGame.getId() == game1.getId()){
                Assertions.assertEquals(game1.getTitle(), videoGame.getTitle(), "The game title matches");
                Assertions.assertEquals(game1.getDeveloper(), videoGame.getDeveloper(), "The developers matches");
                Assertions.assertEquals(game1.getGenre(), videoGame.getGenre(), "The game genre matches");
                Assertions.assertEquals(game1.getYear(), videoGame.getYear(), "The game year matches");
                Assertions.assertEquals(game1.getDescription(), videoGame.getDescription(), "The game description matches");
                Assertions.assertEquals(game1.getCountry(), videoGame.getCountry(), "The game country matches");
                Assertions.assertEquals(game1.getPlatforms(), videoGame.getPlatforms(), "The game platforms matches");
                Assertions.assertEquals(game1.getTags(), videoGame.getTags(), "The game tags matches");
                Assertions.assertEquals(game1.getImage(), videoGame.getImage(), "The game image matches");

            } else if(videoGame.getId() == game2.getId()){
                Assertions.assertEquals(game2.getTitle(), videoGame.getTitle(), "The game title matches");
                Assertions.assertEquals(game2.getDeveloper(), videoGame.getDeveloper(), "The developers matches");
                Assertions.assertEquals(game2.getGenre(), videoGame.getGenre(), "The game genre matches");
                Assertions.assertEquals(game2.getYear(), videoGame.getYear(), "The game year matches");
                Assertions.assertEquals(game2.getDescription(), videoGame.getDescription(), "The game description matches");
                Assertions.assertEquals(game2.getCountry(), videoGame.getCountry(), "The game country matches");
                Assertions.assertEquals(game2.getPlatforms(), videoGame.getPlatforms(), "The game platforms matches");
                Assertions.assertEquals(game2.getTags(), videoGame.getTags(), "The game tags matches");
                Assertions.assertEquals(game2.getImage(), videoGame.getImage(), "The game image matches");

            }else {
                Assertions.assertTrue(false, "Video Game returned is not in the records!");
            }
        }
    }
}
