package com.kenzie.appserver.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kenzie.appserver.service.model.VideoGame;

import java.util.concurrent.TimeUnit;
//For videogame
public class CacheStore {
    public Cache<String, VideoGame> cache;

    public CacheStore(int expiry, TimeUnit timeUnit) {
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiry, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public VideoGame get(String key) {
        return cache.getIfPresent(key);
    }

    public void evict(String key) {
        if (key != null) {
            cache.invalidate(key);
        }
    }

    public void add(String key, VideoGame value) {
        if (key != null) {
            cache.put(key, value);
        }
    }
}