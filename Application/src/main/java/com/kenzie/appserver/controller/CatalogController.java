package com.kenzie.appserver.controller;


import com.kenzie.appserver.controller.model.*;
import com.amazonaws.Response;
import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
import com.kenzie.appserver.service.VideoGameCatalogService;
import com.kenzie.appserver.service.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            return ResponseEntity.noContent().build();
        }

        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);
        return ResponseEntity.ok(videoGameResponse);
}


    @GetMapping("{title}")           //     http://localhost:8000/games/title will pull this up.
    public ResponseEntity<VideoGameResponse> searchByTitle(@PathVariable("title") String title) {
        VideoGame videoGame = catalogService.findGameByTitle(title);

        if (videoGame == null) {
            return ResponseEntity.noContent().build();                                   // If there are no titles, return a 204 error.
        }

        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);       // If there is a match, convert the title into a VideoGameResponse and return it.
        return ResponseEntity.ok(videoGameResponse);
    }
    @GetMapping("{developer}")  //     http://localhost:8000/games/title will pull this up.
    public ResponseEntity<VideoGameResponse> searchByDeveloper(@PathVariable("developer") String developer){
        VideoGame videoGame = catalogService.findGameByDeveloper(developer);

        if (videoGame == null) {                            // If there are no developers, return a 204 error.
            return ResponseEntity.noContent().build();
        }
        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);       // If there is a match, convert the title into a VideoGameResponse and return it.
        return ResponseEntity.ok(videoGameResponse);
    }
    @GetMapping("{genre}")  //     http://localhost:8000/games/title will pull this up.
    public ResponseEntity<VideoGameResponse> searchByGenre(@PathVariable("genre") String genre){
        VideoGame videoGame = catalogService.findGameByGenre(genre);

        if (videoGame == null) {                            // If there are no genre, return a 204 error.
            return ResponseEntity.noContent().build();
        }
        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);       // If there is a match, convert the title into a VideoGameResponse and return it.
        return ResponseEntity.ok(videoGameResponse);
    }
    @GetMapping("{platforms}")  //     http://localhost:8000/games/title will pull this up.
    public ResponseEntity<VideoGameResponse> searchByPlatforms(@PathVariable("platforms") String platforms){
        VideoGame videoGame = catalogService.findGameByPlatforms(platforms);

        if (videoGame == null) {                            // If there are no genre, return a 204 error.
            return ResponseEntity.noContent().build();
        }
        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);       // If there is a match, convert the title into a VideoGameResponse and return it.
        return ResponseEntity.ok(videoGameResponse);
    }
    @GetMapping("{tags}")  //     http://localhost:8000/games/title will pull this up.
    public ResponseEntity<VideoGameResponse> searchByTag(@PathVariable("tags") String tags){
        VideoGame videoGame = catalogService.findGameByTag(tags);

        if (videoGame == null) {                            // If there are no genre, return a 204 error.
            return ResponseEntity.noContent().build();
        }
        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);       // If there is a match, convert the title into a VideoGameResponse and return it.
        return ResponseEntity.ok(videoGameResponse);
    }
    @GetMapping("{country}")  //     http://localhost:8000/games/title will pull this up.
    public ResponseEntity<VideoGameResponse> searchByCountry(@PathVariable("country") String country){
        VideoGame videoGame = catalogService.findGameByCountry(country);

        if (videoGame == null) {                            // If there are no genre, return a 204 error.
            return ResponseEntity.noContent().build();
        }
        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);       // If there is a match, convert the title into a VideoGameResponse and return it.
        return ResponseEntity.ok(videoGameResponse);
    }

    @GetMapping("{year}")  //     http://localhost:8000/games/title will pull this up.
    public ResponseEntity<VideoGameResponse> searchByYear(@PathVariable("year") String year){
        VideoGame videoGame = catalogService.findGamesByYear(year);

        if (videoGame == null) {                            // If there are no genre, return a 204 error.
            return ResponseEntity.noContent().build();
        }
        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);       // If there is a match, convert the title into a VideoGameResponse and return it.
        return ResponseEntity.ok(videoGameResponse);
    }

    @GetMapping("all")      //     http://localhost:8000/games/title will pull this up.
    public ResponseEntity<List<VideoGameResponse>> getAllGames() {
        Collection<VideoGame> allGames = catalogService.findAllGames();

        List<VideoGameResponse> response = allGames.stream()
                .map(this::createVideoGameResponse)
                .collect(Collectors.toList());

        return Optional.ofNullable(response)                        // If there is a match, convert the title into a VideoGameResponse and return it.
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());     // If there are no genre, return a 204 error.
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
