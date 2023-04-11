package com.kenzie.appserver.controller;

<<<<<<< HEAD
<<<<<<< HEAD

<<<<<<< HEAD

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

import com.kenzie.appserver.controller.model.VideoGameCreateRequest;
import com.kenzie.appserver.controller.model.VideoGameResponse;
import com.kenzie.appserver.controller.model.VideoGameUpdateRequest;
import com.kenzie.appserver.service.VideoGameCatalogService;
import com.kenzie.appserver.service.model.VideoGame;

=======
=======

>>>>>>> 734c590 (niocledkjkodso)
=======
<<<<<<< HEAD
>>>>>>> c1bcad1 (nojdijsjeoij)
import com.kenzie.appserver.controller.model.CatalogCreateRequest;
import com.kenzie.appserver.controller.model.CatalogResponse;
<<<<<<< HEAD
import com.kenzie.appserver.controller.model.CatalogUpdateRequest;
import com.kenzie.appserver.repositories.CatalogRepository;
import com.kenzie.appserver.service.CatalogService;
import com.kenzie.appserver.service.model.Game;
<<<<<<< HEAD
>>>>>>> 230d7be (Completely recreated all classes that have to do with the Video Game Catalog. Kept original classes but COMMENTED THEM OUT SO THEY DON'T AFFECT CODE: VideoGameCreateRequest -> CatalogCreateRequest, VideoGameResponse -> CatalogResponse, VideoGameUpdateRequest -> CatalogUpdateRequest, CatalogControllerOriginal -> CatalogController, VideoGameCatalogRepository -> CatalogRepository, VideoGame -> Game , VideoGameCatalogService -> CatalogService. Commented out ALL tests to run app. Successfully able to Post, Get, Put, and Delete.)
=======
=======
=======
import com.kenzie.appserver.controller.model.*;
>>>>>>> 34de5ae (nicole stuff)
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
>>>>>>> 07b1034 (nicoles work)
>>>>>>> 734c590 (niocledkjkodso)
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 734c590 (niocledkjkodso)
// FIXME - I spoke with Melissa about this, but we may change the partition key from the 'title' to 'ID'. If we do that, the names of these methods are going to change.
// FIXME - Change them from 'searchByTitle' to 'searchByID' etc...
//Could we maybe keep the searchByTitle and add the searchById?

<<<<<<< HEAD
=======
>>>>>>> 230d7be (Completely recreated all classes that have to do with the Video Game Catalog. Kept original classes but COMMENTED THEM OUT SO THEY DON'T AFFECT CODE: VideoGameCreateRequest -> CatalogCreateRequest, VideoGameResponse -> CatalogResponse, VideoGameUpdateRequest -> CatalogUpdateRequest, CatalogControllerOriginal -> CatalogController, VideoGameCatalogRepository -> CatalogRepository, VideoGame -> Game , VideoGameCatalogService -> CatalogService. Commented out ALL tests to run app. Successfully able to Post, Get, Put, and Delete.)
=======
>>>>>>> 07b1034 (nicoles work)
>>>>>>> 734c590 (niocledkjkodso)
@RestController
@RequestMapping("/games")
public class CatalogController {

    private CatalogService catalogService;

    CatalogController(CatalogService catalogService) {

        this.catalogService = catalogService;

<<<<<<< HEAD
//    @GetMapping("/{gameId}")
//    public ResponseEntity<VideoGameResponse> searchGameById (@PathVariable("gameId") String gameId) {
//        VideoGame game = catalogService.findGameById(gameId);
//        if(game == null){
//            return ResponseEntity.notFound().build();
//        }
//
//        VideoGameResponse videoGameResponse = createVideoGameResponse(game);
//        return ResponseEntity.ok(videoGameResponse);
//        VideoGameResponse catalogResponse = new VideoGameResponse();
//        catalogResponse.setId(game.getId());
//        catalogResponse.setTitle(game.getGameTitle());
//        catalogResponse.setDeveloper(game.getDeveloper());
//        catalogResponse.setGenre(game.getGenre());
//        catalogResponse.setYear(game.getYear());
//        catalogResponse.setPlatforms(game.getPlatforms());
//        catalogResponse.setTags(game.getTags());
//        catalogResponse.setCountry(game.getCountry());
//        return ResponseEntity.ok(catalogResponse);
//    }


    private VideoGameResponse createVideoGameResponse(VideoGame videoGame) {
        VideoGameResponse videoGameResponse = new VideoGameResponse();
        videoGameResponse.setId(videoGame.getId());
        videoGameResponse.setTitle(videoGame.getGameTitle());
        videoGameResponse.setDeveloper((videoGame.getDeveloper()));
        videoGameResponse.setGenre(videoGame.getGenre());
        videoGameResponse.setYear(videoGame.getYear());
        videoGameResponse.setPlatforms(videoGame.getPlatforms());
        videoGameResponse.setTags(videoGame.getTags());
        videoGameResponse.setDescription(videoGame.getDescription());
        videoGameResponse.setCountry(videoGame.getCountry());
        return videoGameResponse;
=======
>>>>>>> 230d7be (Completely recreated all classes that have to do with the Video Game Catalog. Kept original classes but COMMENTED THEM OUT SO THEY DON'T AFFECT CODE: VideoGameCreateRequest -> CatalogCreateRequest, VideoGameResponse -> CatalogResponse, VideoGameUpdateRequest -> CatalogUpdateRequest, CatalogControllerOriginal -> CatalogController, VideoGameCatalogRepository -> CatalogRepository, VideoGame -> Game , VideoGameCatalogService -> CatalogService. Commented out ALL tests to run app. Successfully able to Post, Get, Put, and Delete.)
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogResponse> searchGameById(@PathVariable("id") String id) {

<<<<<<< HEAD
        if (videoGame == null) {
            return ResponseEntity.noContent().build();
=======
        Game game = catalogService.findByGameId(id);

        // If there are no games, then return a 204.
        if (game == null) {
            return ResponseEntity.notFound().build();
>>>>>>> 230d7be (Completely recreated all classes that have to do with the Video Game Catalog. Kept original classes but COMMENTED THEM OUT SO THEY DON'T AFFECT CODE: VideoGameCreateRequest -> CatalogCreateRequest, VideoGameResponse -> CatalogResponse, VideoGameUpdateRequest -> CatalogUpdateRequest, CatalogControllerOriginal -> CatalogController, VideoGameCatalogRepository -> CatalogRepository, VideoGame -> Game , VideoGameCatalogService -> CatalogService. Commented out ALL tests to run app. Successfully able to Post, Get, Put, and Delete.)
        }

        // Otherwise, convert it into a CatalogResponse and return it.
        CatalogResponse catalogResponse = createCatalogResponse(game);

        return ResponseEntity.ok(catalogResponse);

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    @GetMapping("/{title}")           //     http://localhost:8000/games/title will pull this up.
=======
=======

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
>>>>>>> c1bcad1 (nojdijsjeoij)
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
<<<<<<< HEAD
=======
//    @GetMapping("/{title}")           //     http://localhost:8000/games/title will pull this up.
//    public ResponseEntity<VideoGameResponse> searchByTitle(@PathVariable("title") String title) {
//        VideoGame videoGame = catalogService.findGameByTitle(title);
//
//        if (videoGame == null) {
//            return ResponseEntity.notFound().build();                                   // If there are no titles, return a 204 error.
//        }
//
//        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);       // If there is a match, convert the title into a VideoGameResponse and return it.
//        return ResponseEntity.ok(videoGameResponse);
//    }
>>>>>>> c52cc7b (Commented out findGameByTitle() in CatalogController.java and ran getGame_GameExists test in VideoGameControllerTest.java and successfully passed.)
=======
>>>>>>> c1bcad1 (nojdijsjeoij)

        if (videoGame == null) {                            // If there are no developers, return a 204 error.
            return ResponseEntity.noContent().build();
        }
        VideoGameResponse videoGameResponse = createVideoGameResponse(videoGame);       // If there is a match, convert the title into a VideoGameResponse and return it.
        return ResponseEntity.ok(videoGameResponse);
    }
    @GetMapping("{genre}")  //     http://localhost:8000/games/title will pull this up.
    public ResponseEntity<VideoGameResponse> searchByGenre(@PathVariable("genre") String genre){
        VideoGame videoGame = catalogService.findGameByGenre(genre);

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c1bcad1 (nojdijsjeoij)
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
<<<<<<< HEAD
=======
    @GetMapping("/all")              //      http://localhost:8000/games/all will pull this up.
>>>>>>> c52cc7b (Commented out findGameByTitle() in CatalogController.java and ran getGame_GameExists test in VideoGameControllerTest.java and successfully passed.)
=======
>>>>>>> c1bcad1 (nojdijsjeoij)
    public ResponseEntity<List<VideoGameResponse>> getAllGames() {
        Collection<VideoGame> allGames = catalogService.findAllGames();

        List<VideoGameResponse> response = allGames.stream()
                .map(this::createVideoGameResponse)
                .collect(Collectors.toList());

        return Optional.ofNullable(response)                        // If there is a match, convert the title into a VideoGameResponse and return it.
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());     // If there are no genre, return a 204 error.
<<<<<<< HEAD
=======
=======
>>>>>>> 34de5ae (nicole stuff)
>>>>>>> c1bcad1 (nojdijsjeoij)
    }

    @PostMapping
    public ResponseEntity<CatalogResponse> addNewGame(@RequestBody CatalogCreateRequest catalogCreateRequest) {
        Game game = new Game(randomUUID().toString(),
                catalogCreateRequest.getTitle(),
                catalogCreateRequest.getDeveloper(),
                catalogCreateRequest.getGenre(),
                catalogCreateRequest.getYear(),
                catalogCreateRequest.getDescription(),
                catalogCreateRequest.getCountry(),
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
                catalogUpdateRequest.getCountry(),
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
>>>>>>> 230d7be (Completely recreated all classes that have to do with the Video Game Catalog. Kept original classes but COMMENTED THEM OUT SO THEY DON'T AFFECT CODE: VideoGameCreateRequest -> CatalogCreateRequest, VideoGameResponse -> CatalogResponse, VideoGameUpdateRequest -> CatalogUpdateRequest, CatalogControllerOriginal -> CatalogController, VideoGameCatalogRepository -> CatalogRepository, VideoGame -> Game , VideoGameCatalogService -> CatalogService. Commented out ALL tests to run app. Successfully able to Post, Get, Put, and Delete.)
    }

    private CatalogResponse createCatalogResponse(Game game) {

        CatalogResponse catalogResponse = new CatalogResponse();
            catalogResponse.setId(game.getId());
            catalogResponse.setTitle(game.getTitle());
            catalogResponse.setDeveloper(game.getDeveloper());
            catalogResponse.setGenre(game.getGenre());
            catalogResponse.setYear(game.getYear());
            catalogResponse.setDescription(game.getDescription());
            catalogResponse.setCountry(game.getCountry());
            catalogResponse.setPlatforms(game.getPlatforms());
            catalogResponse.setTags(game.getTags());

        return catalogResponse;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGameById(@PathVariable("id") String id) {

<<<<<<< HEAD
<<<<<<< HEAD
        catalogService.updateGame(videoGame);

        VideoGameResponse gameResponse = createVideoGameResponse(videoGame);

        return ResponseEntity.ok(gameResponse);
    }


    @DeleteMapping("/{title}")
    public ResponseEntity deleteConcertByTitle(@PathVariable("title") String title) {
        catalogService.deleteGameById(title);
        return ResponseEntity.status(204).build();
    }


    @DeleteMapping("/{gameId}")
    public ResponseEntity deleteGameById(@PathVariable("gameId") String gameId) {
        // Your code here
        catalogService.deleteGameById(gameId);
=======
        catalogService.deleteGame(game);
>>>>>>> 230d7be (Completely recreated all classes that have to do with the Video Game Catalog. Kept original classes but COMMENTED THEM OUT SO THEY DON'T AFFECT CODE: VideoGameCreateRequest -> CatalogCreateRequest, VideoGameResponse -> CatalogResponse, VideoGameUpdateRequest -> CatalogUpdateRequest, CatalogControllerOriginal -> CatalogController, VideoGameCatalogRepository -> CatalogRepository, VideoGame -> Game , VideoGameCatalogService -> CatalogService. Commented out ALL tests to run app. Successfully able to Post, Get, Put, and Delete.)
=======
        catalogService.deleteGame(id);
>>>>>>> e2cd2fa (Fixed tests and delete method in catalogservice)
        return ResponseEntity.status(204).build();

    }

<<<<<<< HEAD
=======
    @DeleteMapping("/{title}")
    public ResponseEntity deleteConcertByTitle(@PathVariable("title") String title) {
        catalogService.deleteGameById(title);
        return ResponseEntity.status(204).build();
    }

>>>>>>> 734c590 (niocledkjkodso)
}
