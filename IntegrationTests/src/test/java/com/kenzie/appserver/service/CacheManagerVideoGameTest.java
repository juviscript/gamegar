package com.kenzie.appserver.service;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.config.CacheStoreUser;
import com.kenzie.appserver.service.model.User;
import com.kenzie.appserver.service.model.VideoGame;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class CacheManagerVideoGameTest {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    VideoGameCatalogService videoGameCatalogService;

    @Autowired
    private CacheStore Cache;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    @Test
    public void videoGameCache_InsertIntoCache() throws Exception {
        String id = UUID.randomUUID().toString();
        String title = mockNeat.strings().valStr();
        String developer = mockNeat.strings().valStr();
        String genre = mockNeat.strings().valStr();
        Integer year = 2000;
        List platforms = new LinkedList();
        List tags = new LinkedList();
        String description = mockNeat.strings().valStr();
        String country = mockNeat.strings().valStr();

        VideoGame videoGame = new VideoGame(id,title,developer,genre,year,platforms,tags,description,country);
        videoGameCatalogService.addNewGame(videoGame);
        videoGameCatalogService.findGameById(id);

        VideoGame videoGameFromCache = Cache.get(videoGame.getId());

        assertThat(videoGameFromCache).isNotNull();
        assertThat(videoGameFromCache.getId()).isEqualTo(id);
    }

//    @Test
//    public void videoGameCacheUpdate_EvictFromCache() throws Exception {
//        String id = UUID.randomUUID().toString();
//        String title = mockNeat.strings().valStr();
//        String developer = mockNeat.strings().valStr();
//        String genre = mockNeat.strings().valStr();
//        Integer year = 2000;
//        List platforms = new LinkedList();
//        List tags = new LinkedList();
//        String description = mockNeat.strings().valStr();
//        String country = mockNeat.strings().valStr();
//
//        VideoGame videoGame = new VideoGame(id,title,developer,genre,year,platforms,tags,description,country);
//        videoGameCatalogService.addNewGame(videoGame);
//        videoGameCatalogService.findGameById(id);
//
//        VideoGame videoGameFromCache = Cache.get(videoGame.getId());
//
//        videoGameCatalogService.updateGame(videoGame);
//
//        VideoGame videoGameFromCacheAfterUpdate = Cache.get(videoGame.getId());
//
//        assertThat(videoGameFromCache).isNotNull();
//        assertThat(videoGameFromCache.getId()).isEqualTo(id);
//        assertThat(videoGameFromCacheAfterUpdate).isNull();
//    }
}

