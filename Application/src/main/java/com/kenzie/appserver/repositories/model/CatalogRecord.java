package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.checkerframework.checker.units.qual.C;

import java.util.Objects;

@DynamoDBTable(tableName = "VideoGame Catalog")
public class CatalogRecord {

    private String gameName;
    private String gameId;
    private String gameDescription;

    @DynamoDBHashKey(attributeName = "gameName")
    public String getGameName() {
        return gameName;
    }
    @DynamoDBRangeKey(attributeName = "gameId")
    public String getGameId(){
        return gameId;
    }
    @DynamoDBAttribute(attributeName = "gameDescription")
    public String getGameDescription(){
        return gameDescription;
    }
    public void setGameName(String name){
        this.gameName = name;
    }
    public void setGameId(String id){
        this.gameId = id;
    }
    public void setGameDescription(String description){
        this.gameDescription = description;
    }
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        CatalogRecord catalogRecord = (CatalogRecord) o;
        return Objects.equals(gameName, catalogRecord.gameName);
    }
    @Override
    public int hashCode(){
        return Objects.hash(gameName);
    }
}
