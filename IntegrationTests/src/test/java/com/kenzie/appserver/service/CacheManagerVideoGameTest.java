package com.kenzie.appserver.service;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.config.CacheStoreUser;
import com.kenzie.appserver.service.model.User;
import com.kenzie.appserver.service.model.Game;
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
    CatalogService catalogService;

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
        String description = mockNeat.strings().valStr();
        String country = mockNeat.strings().valStr();
        String platforms = mockNeat.strings().valStr();
        String tags = mockNeat.strings().valStr();
        String image = mockNeat.strings().valStr();

        Game game = new Game(id,title,developer,genre,year,description,country,platforms,tags,image);
        catalogService.addNewGame(game);
       catalogService.findByGameId(id);

        Game gameFromCache = Cache.get(game.getId());

        assertThat(gameFromCache).isNotNull();
        assertThat(gameFromCache.getId()).isEqualTo(id);
    }

    @Test
    public void videoGameCacheUpdate_EvictFromCache() throws Exception {
        String id = UUID.randomUUID().toString();
        String title = mockNeat.strings().valStr();
        String developer = mockNeat.strings().valStr();
        String genre = mockNeat.strings().valStr();
        Integer year = 2000;
        String description = mockNeat.strings().valStr();
        String country = mockNeat.strings().valStr();
        String platforms = mockNeat.strings().valStr();
        String tags = mockNeat.strings().valStr();
        String image = mockNeat.strings().valStr();

        Game game = new Game(id,title,developer,genre,year,description,country,platforms,tags,image);
        catalogService.addNewGame(game);
        catalogService.findByGameId(id);

        Game gameFromCache = Cache.get(game.getId());

        catalogService.updateGame(game);

        Game gameFromCacheAfterUpdate = Cache.get(game.getId());

        assertThat(gameFromCache).isNotNull();
        assertThat(gameFromCache.getId()).isEqualTo(id);
        assertThat(gameFromCacheAfterUpdate).isNull();
    }
}

