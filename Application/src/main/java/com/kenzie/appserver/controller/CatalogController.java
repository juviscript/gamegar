package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CatalogCreateRequest;
import com.kenzie.appserver.controller.model.CatalogReponse;

import com.kenzie.appserver.service.VideoGameService;
import com.kenzie.appserver.service.model.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.util.UUID.randomUUID;

//TODO need to make Response and Request
@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private VideoGameService videoGameService;

    CatalogController(VideoGameService videoGameService){
        this.videoGameService = videoGameService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<CatalogReponse>get(@PathVariable("name") String id){

        Game game = videoGameService.findById(id);
        if(game == null){
            return ResponseEntity.notFound().build();
        }
        CatalogReponse catalogReponse = new CatalogReponse();
        catalogReponse.setId(game.getGameId());
        catalogReponse.setName(game.getGameName());
        catalogReponse.setDescription(game.getGameDescription());
        return ResponseEntity.ok(catalogReponse);
    }
    @PostMapping
    public ResponseEntity<CatalogReponse> addNewGame(@RequestBody CatalogCreateRequest catalogCreateRequest){
        Game game = new Game(randomUUID().toString(),
                catalogCreateRequest.getName(), catalogCreateRequest.getDescription());
        videoGameService.addNewGame(game);

        CatalogReponse catalogReponse = new CatalogReponse();
        catalogReponse.setId(game.getGameId());
        catalogReponse.setName(game.getGameName());
        catalogReponse.setDescription(game.getGameDescription());

        return ResponseEntity.created(URI.create("/game/" + catalogReponse.getId())).body(catalogReponse);
    }
}
