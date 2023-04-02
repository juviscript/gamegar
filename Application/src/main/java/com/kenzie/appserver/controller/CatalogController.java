package com.kenzie.appserver.controller;


import com.kenzie.appserver.controller.model.CatalogCreateRequest;
import com.kenzie.appserver.controller.model.CatalogResponse;
import com.amazonaws.Response;
import com.kenzie.appserver.controller.model.VideoGameCreateRequest;
import com.kenzie.appserver.controller.model.VideoGameResponse;
import com.kenzie.appserver.controller.model.VideoGameUpdateRequest;
import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
import com.kenzie.appserver.service.VideoGameCatalogService;
import com.kenzie.appserver.service.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

// FIXME - I spoke with Melissa about this, but we may change the partition key from the 'title' to 'ID'. If we do that, the names of these methods are going to change.
// FIXME - Change them from 'searchByTitle' to 'searchByID' etc...
//Could we maybe keep the searchByTitle and add the searchById?

@RestController
@RequestMapping("/games")           //  This is the API extension name (ex: http://localhost:8000/games) will pull this up.
public class CatalogController {
    private VideoGameCatalogService catalogService;

    CatalogController(VideoGameCatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<VideoGameResponse> get(@PathVariable("name") String id) {
        VideoGame game = catalogService.findGameById(id);
        if(game == null){
            return ResponseEntity.notFound().build();
        }
        VideoGameResponse catalogResponse = new VideoGameResponse();
        catalogResponse.setId(game.getId());
        catalogResponse.setTitle(game.getGameTitle());
        catalogResponse.setDeveloper(game.getDeveloper());
        catalogResponse.setGenre(game.getGenre());
        catalogResponse.setYear(game.getYear());
        catalogResponse.setPlatforms(game.getPlatforms());
        catalogResponse.setTags(game.getTags());
        catalogResponse.setCountry(game.getCountry());
        return ResponseEntity.ok(catalogResponse);
    }

    private VideoGameResponse createVideoGameResponse(VideoGame videoGame) {
        VideoGameResponse videoGameResponse = new VideoGameResponse();
        videoGameResponse.setId(videoGame.getId());
        videoGameResponse.setTitle(videoGame.getGameTitle());
        videoGameResponse.setDeveloper((videoGame.getDeveloper()));
        videoGameResponse.setGenre(videoGame.getGenre());
        videoGameResponse.setYear(videoGame.getYear());
        videoGameResponse.setPlatforms(videoGame.getPlatforms());
        videoGameResponse.setTags(videoGame.getTags());
        videoGameResponse.setDescription(videoGame.getDescription());        //  TODO: Response: Should we keep description and country as something that is required to create a new instance
        videoGameResponse.setCountry(videoGame.getCountry());
        return videoGameResponse;                                            //         of a game?
    }

    @GetMapping("{id}")
    public ResponseEntity<VideoGameResponse> searchById(@PathVariable("id") String id) {
        VideoGame videoGame = catalogService.findGameById(id);

        if (videoGame == null) {
            return ResponseEntity.notFound().build();
        }

        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);
        return ResponseEntity.ok(videoGameResponse);
}


    @GetMapping("{title}")           //     http://localhost:8000/games/title will pull this up.
    public ResponseEntity<VideoGameResponse> searchByTitle(@PathVariable("title") String title) {
        VideoGame videoGame = catalogService.findGameByTitle(title);

        if (videoGame == null) {
            return ResponseEntity.notFound().build();                                   // If there are no titles, return a 204 error.
        }

        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);       // If there is a match, convert the title into a VideoGameResponse and return it.
        return ResponseEntity.ok(videoGameResponse);
    }


    @GetMapping("all")              //      http://localhost:8000/games/all will pull this up.
    public ResponseEntity<List<VideoGameResponse>> getAllGames() {
        List<VideoGame> allGames = catalogService.findAllGames();

        if (allGames == null || allGames.isEmpty()) {                                // If no games or all are listed as 'null', return a 204 response.
            return ResponseEntity.status(204).build();
        }

        List<VideoGameResponse> response = new ArrayList<>();
        for (VideoGame game : allGames) {
            response.add(this.createVideoGameResponse(game));                         // Otherwise, return list.
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<VideoGameResponse> addNewGame(@RequestBody VideoGameCreateRequest gameCreateRequest) {
        VideoGame videoGame = new VideoGame(randomUUID().toString(),
                gameCreateRequest.getTitle(),
                gameCreateRequest.getDeveloper(),
                gameCreateRequest.getGenre(),
                gameCreateRequest.getYear(),
                gameCreateRequest.getPlatforms(),
                gameCreateRequest.getTags(),
                gameCreateRequest.getDescription(),
                gameCreateRequest.getCountry());

        catalogService.addNewGame(videoGame);

        VideoGameResponse gameResponse = createVideoGameResponse(videoGame);

        return ResponseEntity.created(URI.create("/games/" + gameResponse.getId())).body(gameResponse);         // Created a new endpoint for specific game instance.

    }

    @PutMapping
    public ResponseEntity<VideoGameResponse> updateGame(@RequestBody VideoGameUpdateRequest gameUpdateRequest) {
        VideoGame videoGame = new VideoGame((gameUpdateRequest.getId()),
                gameUpdateRequest.getGameTitle(),
                gameUpdateRequest.getDeveloper(),
                gameUpdateRequest.getGenre(),
                gameUpdateRequest.getYear(),
                gameUpdateRequest.getPlatforms(),
                gameUpdateRequest.getTags(),
                gameUpdateRequest.getDescription(),
                gameUpdateRequest.getCountry());

        catalogService.updateGame(videoGame);

        VideoGameResponse gameResponse = createVideoGameResponse(videoGame);

        return ResponseEntity.ok(gameResponse);
    }

    @DeleteMapping("/{title}")
    public ResponseEntity deleteConcertByTitle(@PathVariable("title") String title) {
        catalogService.deleteGameById(title);
        return ResponseEntity.status(204).build();
    }

}
