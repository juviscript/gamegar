package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CatalogCreateRequest;
import com.kenzie.appserver.controller.model.CatalogResponse;

import com.kenzie.appserver.service.VideoGameService;
import com.kenzie.appserver.service.model.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

//TODO need to make Response and Request
@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private VideoGameService videoGameService;

    CatalogController(VideoGameService videoGameService){
        this.videoGameService = videoGameService;
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<CatalogResponse>get(@PathVariable("name") String id){

        Game game = videoGameService.findById(id);
        if(game == null){
            return ResponseEntity.notFound().build();
        }
        CatalogResponse catalogResponse = new CatalogResponse();
        catalogResponse.setId(game.getGameId());
        catalogResponse.setName(game.getGameName());
        catalogResponse.setDescription(game.getGameDescription());
        return ResponseEntity.ok(catalogResponse);
    }
    @PostMapping
    public ResponseEntity<CatalogResponse> addNewGame(@RequestBody CatalogCreateRequest catalogCreateRequest){
        Game game = new Game(randomUUID().toString(),
                catalogCreateRequest.getName(), catalogCreateRequest.getDescription());
        videoGameService.addNewGame(game);

        CatalogResponse catalogResponse = new CatalogResponse();
        catalogResponse.setId(game.getGameId());
        catalogResponse.setName(game.getGameName());
        catalogResponse.setDescription(game.getGameDescription());

        return ResponseEntity.created(URI.create("/game/" + catalogResponse.getId())).body(catalogResponse);
    }
    @GetMapping
    public ResponseEntity<List<CatalogResponse>> getAllGames(){
        List<Game> games = videoGameService.findAllGames();
        if(games == null || games.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<CatalogResponse> response = new ArrayList<>();
        for(Game listOfGames : games){
            response.add(this.createGameResponse(listOfGames));
        }
        return ResponseEntity.ok(response);
    }
    public CatalogResponse createGameResponse(Game game){
        CatalogResponse catalogResponse = new CatalogResponse();
        catalogResponse.setId(game.getGameId());
        catalogResponse.setName(game.getGameName());
        catalogResponse.setDescription(game.getGameDescription());
        return catalogResponse;
    }
}
