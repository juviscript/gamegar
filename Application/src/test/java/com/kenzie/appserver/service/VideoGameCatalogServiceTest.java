//package com.kenzie.appserver.service;
//
//import com.kenzie.appserver.config.CacheStore;
//import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
//import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
//import com.kenzie.appserver.service.model.VideoGame;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Optional;
//
//import static java.util.UUID.randomUUID;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.*;
//
//public class VideoGameCatalogServiceTest {
//
//    private VideoGameCatalogRepository videoGameCatalogRepository;
//    private CacheStore cacheStore;
//    private VideoGameCatalogService videoGameCatalogService;
//
//    @BeforeEach
//    void setup() {
//        videoGameCatalogRepository = mock(VideoGameCatalogRepository.class);
//        cacheStore = mock(CacheStore.class);
//        videoGameCatalogService = new VideoGameCatalogService(videoGameCatalogRepository, cacheStore);
//    }
//
//    @Test
//    void deleteGame_null_input() {
//        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
//                videoGameCatalogService.deleteGameById(null));
//    }
//
//    @Test
//    void deleteGame() {
//        String gameId = "1";
//        List<String> test = new LinkedList<>();
//        test.add("example");
//        test.add("example2");
//
//        VideoGameCatalogRecord record = new VideoGameCatalogRecord();
//        record.setId(gameId);
//
//        Mockito.when(videoGameCatalogRepository.existsById(anyString())).thenReturn(true);
//        Mockito.when(videoGameCatalogRepository.findById(anyString())).thenReturn(Optional.of(record));
//
//        when(cacheStore.get(record.getGameId())).thenReturn(new VideoGame(gameId, "name", "1",
//                "action", 2007, test, test, "cute game", "US"));
//
//        videoGameCatalogService.deleteGameById(gameId);
//
//        verify(cacheStore).evict(record.getGameId());
//
//        verify(videoGameCatalogRepository, times(1)).deleteById(gameId);
//    }
//
//    @Test
//    void videoGameCatalogService_findByGameId_idIsNull() {
//        // GIVEN
//        VideoGameCatalogRecord nullIdGame = new VideoGameCatalogRecord();
//        nullIdGame.setId(null);
//
//        // WHEN
//        when(videoGameCatalogRepository.findById(nullIdGame.getGameId())).thenReturn(Optional.empty());
//        VideoGame response = videoGameCatalogService.findGameById(nullIdGame.getGameId());
//
//        //THEN
//        Assertions.assertNull(response);
//    }
//
//    @Test
//    void findByGameId() {
//        // GIVEN
//        String gameId = randomUUID().toString();
//
//        VideoGameCatalogRecord record = new VideoGameCatalogRecord();
//        record.setId(gameId);
//        record.setTitle("gametitle");
//        record.setYear(2007);
//        record.setDeveloper("bobby");
//        record.setDescription("great game");
//        record.setGenre("action");
//        record.setPlatforms(new LinkedList<>());
//        record.setTags(new LinkedList<>());
//        record.setCountry("US");
//
//        when(videoGameCatalogRepository.findById(gameId)).thenReturn(Optional.of(record));
//        // WHEN
//        VideoGame videoGame = videoGameCatalogService.findGameById(gameId);
//
//        // THEN
//        Assertions.assertNotNull(videoGame, "The video game is returned");
//        assertEquals(record.getGameId(), videoGame.getId(), "The video game id matches");
//        assertEquals(record.getGameTitle(), videoGame.getGameTitle(), "The video game title matches");
//        assertEquals(record.getDeveloper(), videoGame.getDeveloper(), "The video game developer matches");
//        assertEquals(record.getGenre(), videoGame.getGenre(), "The video game genre matches");
//        assertEquals(record.getYear(), videoGame.getYear(), "The video game year matches");
//        assertEquals(record.getPlatforms(), videoGame.getPlatforms(), "The video game platform matches");
//        assertEquals(record.getTags(), videoGame.getTags(), "The video game tags match");
//        assertEquals(record.getGameDescription(), videoGame.getDescription(), "The video game description matches");
//        assertEquals(record.getCountry(), videoGame.getCountry(), "The video game country matches");
//    }
//
//    @Test
//    void addNewGame() {
//        // GIVEN
//        String gameId = randomUUID().toString();
//
//        VideoGame videoGame = new VideoGame(gameId, "Kirby", "Nintendo", "Adventure", 2022,
//                new LinkedList<>(), new LinkedList<>(),"Kirby is super cute", "Japan");
//
//        ArgumentCaptor<VideoGameCatalogRecord> gameRecordCaptor = ArgumentCaptor.forClass(VideoGameCatalogRecord.class);
//
//        // WHEN
//        VideoGame returnedGame = videoGameCatalogService.addNewGame(videoGame);
//
//        // THEN
//        Assertions.assertNotNull(returnedGame);
//
//        verify(videoGameCatalogRepository).save(gameRecordCaptor.capture());
//
//        VideoGameCatalogRecord record = gameRecordCaptor.getValue();
//
//        Assertions.assertNotNull(record, "The game record is returned");
//        assertEquals(record.getGameId(), videoGame.getId(), "The game id matches");
//        assertEquals(record.getGameTitle(), videoGame.getGameTitle(), "The video game title matches");
//        assertEquals(record.getDeveloper(), videoGame.getDeveloper(), "The video game developer matches");
//        assertEquals(record.getGenre(), videoGame.getGenre(), "The video game genre matches");
//        assertEquals(record.getYear(), videoGame.getYear(), "The video game year matches");
//        //assertEquals(record.getPlatforms(), videoGame.getPlatforms(), "The video game platform matches");
//        assertEquals(record.getTags(), videoGame.getTags(), "The video game tags match");
//        assertEquals(record.getGameDescription(), videoGame.getDescription(), "The video game description matches");
//        //assertEquals(record.getCountry(), videoGame.getCountry(), "The video game country matches");
//    }
//
//    @Test
//    public void updateGame_validGame_gameUpdated() {
//
//        String gameId = randomUUID().toString();
//
//        VideoGame videoGame = new VideoGame(gameId, "gametitle", "developer", "genre", 2000,
//                new LinkedList<>(), new LinkedList<>(),"description", "country");
//
//        VideoGameCatalogRecord videoGameCatalogRecord = new VideoGameCatalogRecord();
//        videoGameCatalogRecord.setId(videoGame.getId());
//        videoGameCatalogRecord.setTitle(videoGame.getGameTitle());
//        videoGameCatalogRecord.setDeveloper(videoGame.getDeveloper());
//        videoGameCatalogRecord.setGenre(videoGame.getGenre());
//        videoGameCatalogRecord.setYear(videoGame.getYear());
//        videoGameCatalogRecord.setPlatforms(videoGame.getPlatforms());
//        videoGameCatalogRecord.setTags(videoGame.getTags());
//        videoGameCatalogRecord.setDescription(videoGame.getDescription());
//        videoGameCatalogRecord.setCountry(videoGame.getCountry());
//
//        when(videoGameCatalogRepository.existsById(videoGame.getId())).thenReturn(true);
//
//        ArgumentCaptor<VideoGameCatalogRecord> argumentCaptor = ArgumentCaptor.forClass(VideoGameCatalogRecord.class);
//        when(videoGameCatalogRepository.findById(videoGame.getId())).thenReturn(Optional.of(videoGameCatalogRecord));
//        when(videoGameCatalogRepository.save(argumentCaptor.capture())).thenReturn(videoGameCatalogRecord);
//        when(cacheStore.get(videoGame.getId())).thenReturn(videoGame);
//
//        videoGameCatalogService.updateGame(videoGame);
//
//        verify(cacheStore).evict(videoGame.getId());
//        verify(videoGameCatalogRepository).existsById(videoGame.getId());
//        verify(videoGameCatalogRepository).save(argumentCaptor.capture());
//        VideoGameCatalogRecord capturedGameRecord = argumentCaptor.getValue();
//        assertEquals(capturedGameRecord.getGameId(), videoGameCatalogRecord.getGameId());
//        assertEquals(capturedGameRecord.getGameTitle(), videoGameCatalogRecord.getGameTitle());
//        assertEquals(capturedGameRecord.getDeveloper(), videoGameCatalogRecord.getDeveloper());
//        assertEquals(capturedGameRecord.getGenre(), videoGameCatalogRecord.getGenre());
//        assertEquals(capturedGameRecord.getYear(), videoGameCatalogRecord.getYear());
//        assertEquals(capturedGameRecord.getPlatforms(), videoGameCatalogRecord.getPlatforms());
//        //assertEquals(capturedGameRecord.getTags(), videoGameCatalogRecord.getTags());
//        assertEquals(capturedGameRecord.getGameDescription(), videoGameCatalogRecord.getGameDescription());
//        assertEquals(capturedGameRecord.getCountry(), videoGameCatalogRecord.getCountry());
//
//    }
//
//     @Test
//    public void findGameByTitle_titleIsNull(){
//        VideoGameCatalogRecord nullGame = new VideoGameCatalogRecord();
//        nullGame.setTitle(null);
//
//        when(videoGameCatalogRepository.findById(nullGame.getGameId())).thenReturn(Optional.empty());
//        VideoGame response = videoGameCatalogService.findGameByTitle(nullGame.getGameTitle());
//
//        Assertions.assertNull(response);
//    }
//    @Test
//    public void findAllGames(){
//        VideoGameCatalogRecord game1 = new VideoGameCatalogRecord();
//        game1.setId(randomUUID().toString());
//        game1.setTitle("Destiny 2");
//        game1.setDeveloper("Bungie");
//        game1.setGenre("Action/Adventure");
//        game1.setYear(2017);
//        game1.setPlatforms(new LinkedList<>());
//        game1.setTags(new LinkedList<>());
//        game1.setDescription("Big shooty shooty space adventure game");
//        game1.setCountry("USA");
//
//        VideoGameCatalogRecord game2 = new VideoGameCatalogRecord();
//        game2.setId(randomUUID().toString());
//        game2.setTitle("The Legend of Zelda: Breath of the Wild");
//        game2.setDeveloper("Nintendo");
//        game2.setGenre("Action/Adventure");
//        game2.setYear(2017);
//        game2.setPlatforms(new LinkedList<>());
//        game2.setTags(new LinkedList<>());
//        game2.setDescription("117 year old boy awakens to save princess from the evil clutches of Ganon");
//        game2.setCountry("Japan");
//
//        List<VideoGameCatalogRecord> records = new ArrayList<>();
//
//        records.add(game1);
//        records.add(game2);
//
//        when(videoGameCatalogRepository.findAll()).thenReturn(records);
//
//        List<VideoGame> recordList = videoGameCatalogService.findAllGames();
//
//        Assertions.assertNotNull(recordList, "The list of games is returned");
//        Assertions.assertEquals(2, recordList.size(), "Here are 2 games");
//
//        for(VideoGame videoGame : recordList) {
//            if(videoGame.getId() == game1.getGameId()){
//                Assertions.assertEquals(game1.getGameTitle(), videoGame.getGameTitle(), "The game title matches");
//                Assertions.assertEquals(game1.getDeveloper(), videoGame.getDeveloper(), "The developers matches");
//                Assertions.assertEquals(game1.getGenre(), videoGame.getGenre(), "The game genre matches");
//                Assertions.assertEquals(game1.getYear(), videoGame.getYear(), "The game year matches");
//                Assertions.assertEquals(game1.getPlatforms(), videoGame.getPlatforms(), "The game platforms matches");
//                Assertions.assertEquals(game1.getTags(), videoGame.getTags(), "The game tags matches");
//                Assertions.assertEquals(game1.getGameDescription(), videoGame.getDescription(), "The game description matches");
//                Assertions.assertEquals(game1.getCountry(), videoGame.getCountry(), "The game country matches");
//            } else if(videoGame.getId() == game2.getGameId()){
//                Assertions.assertEquals(game2.getGameTitle(), videoGame.getGameTitle(), "The game title matches");
//                Assertions.assertEquals(game2.getDeveloper(), videoGame.getDeveloper(), "The developers matches");
//                Assertions.assertEquals(game2.getGenre(), videoGame.getGenre(), "The game genre matches");
//                Assertions.assertEquals(game2.getYear(), videoGame.getYear(), "The game year matches");
//                Assertions.assertEquals(game2.getPlatforms(), videoGame.getPlatforms(), "The game platforms matches");
//                Assertions.assertEquals(game2.getTags(), videoGame.getTags(), "The game tags matches");
//                Assertions.assertEquals(game2.getGameDescription(), videoGame.getDescription(), "The game description matches");
//                Assertions.assertEquals(game2.getCountry(), videoGame.getCountry(), "The game country matches");
//            }else {
//                Assertions.assertTrue(false, "Video Game returned is not in the records!");
//            }
//        }
//    }
//}
