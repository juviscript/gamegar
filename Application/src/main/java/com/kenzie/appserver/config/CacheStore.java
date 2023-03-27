package com.kenzie.appserver.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kenzie.appserver.service.model.Game;

import java.util.concurrent.TimeUnit;

public class CacheStore {
    private Cache<String, Game> cache;

    public CacheStore(int expiry, TimeUnit timeUnit){
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiry,timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }
    public Game get(String key){
        return cache.getIfPresent(key);
    }
    public void evict(String key){
        cache.invalidate(key);
    }
    public void add(String key, Game value){
        cache.put(key,value);
    }
}
