package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CatalogCreateRequest;
import com.kenzie.appserver.controller.model.CatalogResponse;
import com.kenzie.appserver.controller.model.CatalogUpdateRequest;
import com.kenzie.appserver.repositories.CatalogRepository;
import com.kenzie.appserver.service.CatalogService;
import com.kenzie.appserver.service.model.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/games")
public class CatalogController {

    private CatalogService catalogService;

    CatalogController(CatalogService catalogService) {

        this.catalogService = catalogService;

    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogResponse> searchGameById(@PathVariable("id") String id) {

        Game game = catalogService.findByGameId(id);

        // If there are no games, then return a 204.
        if (game == null) {
            return ResponseEntity.notFound().build();
        }

        // Otherwise, convert it into a CatalogResponse and return it.
        CatalogResponse catalogResponse = createCatalogResponse(game);

        return ResponseEntity.ok(catalogResponse);

    }

    @PostMapping
    public ResponseEntity<CatalogResponse> addNewGame(@RequestBody CatalogCreateRequest catalogCreateRequest) {
        Game game = new Game(randomUUID().toString(),
                catalogCreateRequest.getTitle(),
                catalogCreateRequest.getDeveloper(),
                catalogCreateRequest.getGenre(),
                catalogCreateRequest.getYear(),
                catalogCreateRequest.getDescription(),
                catalogCreateRequest.getPlatforms(),
                catalogCreateRequest.getTags());

        catalogService.addNewGame(game);

        CatalogResponse catalogResponse = createCatalogResponse(game);

        return ResponseEntity.created(URI.create("/games/" + catalogResponse.getId())).body(catalogResponse);

    }

    @PutMapping
    public ResponseEntity<CatalogResponse> updateGame(@RequestBody CatalogUpdateRequest catalogUpdateRequest) {

        Game game = new Game(catalogUpdateRequest.getId(),
                catalogUpdateRequest.getTitle(),
                catalogUpdateRequest.getDeveloper(),
                catalogUpdateRequest.getGenre(),
                catalogUpdateRequest.getYear(),
                catalogUpdateRequest.getDescription(),
                catalogUpdateRequest.getPlatforms(),
                catalogUpdateRequest.getTags());

        catalogService.updateGame(game);

        CatalogResponse concertResponse = createCatalogResponse(game);

        return ResponseEntity.ok(concertResponse);

    }

    @GetMapping
    public ResponseEntity<List<CatalogResponse>> getAllGames() {

        List<Game> games = catalogService.findAllGames();

        // If there are no games, then return a 204.
            if (games == null ||  games.isEmpty()) {
                return ResponseEntity.status(204).build();
            }

        // Otherwise, convert the List of Game objects into a List of CatalogResponses and return it.
        List<CatalogResponse> response = new ArrayList<>();

            for (Game game : games) {
                response.add(this.createCatalogResponse(game));
            }

        return ResponseEntity.ok(response);
    }

    private CatalogResponse createCatalogResponse(Game game) {

        CatalogResponse catalogResponse = new CatalogResponse();
            catalogResponse.setId(game.getId());
            catalogResponse.setTitle(game.getTitle());
            catalogResponse.setDeveloper(game.getDeveloper());
            catalogResponse.setGenre(game.getGenre());
            catalogResponse.setYear(game.getYear());
            catalogResponse.setDescription(game.getDescription());
            catalogResponse.setPlatforms(game.getPlatforms());
            catalogResponse.setTags(game.getTags());

        return catalogResponse;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGameById(@PathVariable("id") String game) {

        catalogService.deleteGame(game);
        return ResponseEntity.status(204).build();

    }
}
