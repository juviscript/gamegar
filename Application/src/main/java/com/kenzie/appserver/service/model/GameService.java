package com.kenzie.appserver.service.model;

public class GameService {
    private final String gameName;
    private final String gameId;
    private final String gameDescription;

    public GameService(String gameName,String gameId, String gameDescription){
        this.gameName = gameName;
        this.gameId = gameId;
        this.gameDescription = gameDescription;
    }
    public String getGameName(){
        return gameName;
    }
    public String getGameId(){
        return gameId;
    }
    public String getGameDescription(){
        return gameDescription;
    }
}
