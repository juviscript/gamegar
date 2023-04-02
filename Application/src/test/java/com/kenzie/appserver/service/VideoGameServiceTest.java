//package com.kenzie.appserver.service;
//
//import com.kenzie.appserver.config.CacheStore;
//import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
//import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
//import com.kenzie.appserver.service.model.VideoGame;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.LinkedList;
//import java.util.Optional;
//
//import static java.util.UUID.randomUUID;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.*;
//
//public class VideoGameServiceTest {
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
////    @Test
////    void videoGameCatalogService_findByGameId_idIsNull() {
////        // GIVEN
////        VideoGameCatalogRecord nullIdGame = new VideoGameCatalogRecord();
////        nullIdGame.setId(null);
////
////        // WHEN
////        when(videoGameCatalogRepository.findById(nullIdGame.getGameId())).thenReturn(Optional.empty());
////        VideoGame response = videoGameCatalogService.findGameById(nullIdGame.getGameId());
////
////        //THEN
////        Assertions.assertNull(response);
////    }
//
////    @Test
////    void findByGameId() {
////        // GIVEN
////        String gameId = randomUUID().toString();
////
////        VideoGameCatalogRecord record = new VideoGameCatalogRecord();
////        record.setId(gameId);
////        record.setTitle("gametitle");
////        record.setYear(2007);
////        record.setDeveloper("bobby");
////        record.setDescription("great game");
////        record.setGenre("action");
////        record.setPlatforms(new LinkedList<>());
////        record.setTags(new LinkedList<>());
////        record.setCountry("us");
////
////        when(videoGameCatalogRepository.findById(gameId)).thenReturn(Optional.of(record));
////        // WHEN
////        VideoGame videoGame = videoGameCatalogService.findGameById(gameId);
////
////        // THEN
////        Assertions.assertNotNull(videoGame, "The video game is returned");
////        assertEquals(record.getGameId(), videoGame.getId(), "The video game id matches");
////        assertEquals(record.getGameTitle(), videoGame.getGameTitle(), "The video game title matches");
////        assertEquals(record.getDate(), concert.getDate(), "The concert date matches");
////        assertEquals(record.getTicketBasePrice(), concert.getTicketBasePrice(), "The concert ticket price matches");
////        assertEquals(record.getReservationClosed(), concert.getReservationClosed(), "The concert reservation closed flag matches");
////    }
////
////
////
////    /** ------------------------------------------------------------------------
////     *  concertService.addNewConcert
////     *  ------------------------------------------------------------------------ **/
////
////    @Test
////    void addNewConcert() {
////        // GIVEN
////        String concertId = randomUUID().toString();
////
////        Concert concert = new Concert(concertId, "concertname", "recorddate", 10.0, false);
////
////        ArgumentCaptor<ConcertRecord> concertRecordCaptor = ArgumentCaptor.forClass(ConcertRecord.class);
////
////        // WHEN
////        Concert returnedConcert = concertService.addNewConcert(concert);
////
////        // THEN
////        Assertions.assertNotNull(returnedConcert);
////
////        verify(concertRepository).save(concertRecordCaptor.capture());
////
////        ConcertRecord record = concertRecordCaptor.getValue();
////
////        Assertions.assertNotNull(record, "The concert record is returned");
////        assertEquals(record.getId(), concert.getId(), "The concert id matches");
////        assertEquals(record.getName(), concert.getName(), "The concert name matches");
////        assertEquals(record.getDate(), concert.getDate(), "The concert date matches");
////        assertEquals(record.getTicketBasePrice(), concert.getTicketBasePrice(), "The concert ticket price matches");
////        assertEquals(record.getReservationClosed(), concert.getReservationClosed(), "The concert reservation closed flag matches");
////    }
////
////    /** ------------------------------------------------------------------------
////     *  concertService.updateConcert
////     *  ------------------------------------------------------------------------ **/
////
////    // Write additional tests here
////    //Help from Michael
////    @Test
////    public void updateConcert_validConcert_concertUpdated() {
////
////        String concertId = randomUUID().toString();
////
////        Concert concert = new Concert(concertId, "concertName", "recordDate", 10.0, false);
////
////        ConcertRecord concertRecord = new ConcertRecord();
////        concertRecord.setId(concert.getId());
////        concertRecord.setDate(concert.getDate());
////        concertRecord.setName(concert.getName());
////        concertRecord.setTicketBasePrice(concert.getTicketBasePrice());
////        concertRecord.setReservationClosed(concert.getReservationClosed());
////
////        when(concertRepository.existsById(concert.getId())).thenReturn(true);
////
////        ArgumentCaptor<ConcertRecord> argumentCaptor = ArgumentCaptor.forClass(ConcertRecord.class);
////        when(concertRepository.findById(concert.getId())).thenReturn(Optional.of(concertRecord));
////        when(concertRepository.save(argumentCaptor.capture())).thenReturn(concertRecord);
////        when(cacheStore.get(concert.getId())).thenReturn(concert);
////
////
////        concertService.updateConcert(concert);
////
////        verify(cacheStore).evict(concert.getId());
////        verify(concertRepository).existsById(concert.getId());
////        verify(concertRepository).save(argumentCaptor.capture());
////        ConcertRecord capturedConcertRecord = argumentCaptor.getValue();
////        assertEquals(capturedConcertRecord.getId(), concertRecord.getId());
////        assertEquals(capturedConcertRecord.getDate(), concertRecord.getDate());
////        assertEquals(capturedConcertRecord.getName(), concertRecord.getName());
////        assertEquals(capturedConcertRecord.getTicketBasePrice(), concertRecord.getTicketBasePrice());
////        assertEquals(capturedConcertRecord.getReservationClosed(), concertRecord.getReservationClosed());
////    }
////
////    /** ------------------------------------------------------------------------
////     *  concertService.deleteConcert
////     *  ------------------------------------------------------------------------ **/
////
////    // Write additional tests here
////    //Help from Blake
////    @Test
////    void deleteGame() {
////        String gameId = "1";
////        VideoGameCatalogRecord record = new VideoGameCatalogRecord();
////        record.setId(gameId);
////
////        Mockito.when(videoGameCatalogRepository.existsById(anyString())).thenReturn(true);
////        Mockito.when(videoGameCatalogRepository.findById(anyString())).thenReturn(Optional.of(record));
////
////        when(cacheStore.get(record.getGameId())).thenReturn(new VideoGame(gameId, "name", "1",
////                "action", 2007, new LinkedList<>(), new LinkedList<>(), "cute game", "US"));
////
////        videoGameCatalogService.deleteGameById(gameId);
////
////        verify(cacheStore).evict(record.getGameId());
////
////        verify(videoGameCatalogRepository, times(1)).deleteById(gameId);
////    }
////
//    @Test
//    void deleteGame_isNull() {
//        String gameId = "1";
//        VideoGameCatalogRecord record = new VideoGameCatalogRecord();
//        record.setId(gameId);
//
//        when(cacheStore.get(gameId)).thenReturn(null);
//
////        concertService.deleteConcert(concertId);
////
////        verify(cacheStore).evict(record.getId());
//
//        assertThrows(IllegalArgumentException.class, () -> videoGameCatalogService.deleteGameById(gameId));
//    }
////
////
////    @Test
////    void deleteConcert_null_input() {
////        // WHEN
////        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
////                concertService.deleteConcert(null));
////
////        // THEN
//////        assertThat("The exception message should match the expected message", exception.getMessage(),
//////                is("oh no"));
////    }
////}
//}
