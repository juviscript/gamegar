package com.kenzie.appserver.controller;


<<<<<<< HEAD



import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
import com.kenzie.appserver.service.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO need to make Response and Request
@RestController
@RequestMapping("/games")           // This is the API extension name (ex: localhost:8000/games) will pull this up.

public class CatalogController {

=======
import com.amazonaws.Response;
import com.kenzie.appserver.controller.model.VideoGameCreateRequest;
import com.kenzie.appserver.controller.model.VideoGameResponse;
import com.kenzie.appserver.controller.model.VideoGameUpdateRequest;
import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
import com.kenzie.appserver.service.VideoGameCatalogService;
import com.kenzie.appserver.service.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

// FIXME - I spoke with Melissa about this, but we may change the partition key from the 'title' to 'ID'. If we do that, the names of these methods are going to change.
// FIXME - Change them from 'searchByTitle' to 'searchByID' etc...


@RestController
@RequestMapping("/games")           //  This is the API extension name (ex: http://localhost:8000/games) will pull this up.
public class CatalogController {
    private VideoGameCatalogService catalogService;

    CatalogController(VideoGameCatalogService catalogService) {
        this.catalogService = catalogService;
    }


    private VideoGameResponse createVideoGameResponse(VideoGame videoGame) {
        VideoGameResponse videoGameResponse = new VideoGameResponse();
        videoGameResponse.setId(videoGame.getId());
        videoGameResponse.setTitle(videoGame.getGameTitle());
        videoGameResponse.setDeveloper((videoGame.getDeveloper()));
        videoGameResponse.setGenre(videoGame.getGenre());
        videoGameResponse.setPlatforms(videoGame.getPlatforms());
        videoGameResponse.setTags(videoGame.getTags());
        videoGameResponse.setDescription(videoGame.getDescription());        //  TODO: Response: Should we keep description as something that is required to create a new instance
        return videoGameResponse;                                            //         of a game?
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


//    @GetMapping("all")              //      http://localhost:8000/games/all will pull this up.
//    public ResponseEntity<List<VideoGameResponse>> getAllGames() {
//       List<VideoGame> allGames = catalogService.findAllGames();
//
//        if (allGames == null || allGames.isEmpty()) {                                // If no games or all are listed as 'null', return a 204 response.
//            return ResponseEntity.status(204).build();
//        }
//
//        List<VideoGameResponse> response = new ArrayList<>();                        TODO: <-- Uncomment this once the findAllGames() method has been created in VideoGameCatalogService.
//        for (VideoGame game : allGames) {
//            response.add(this.createVideoGameResponse(game));                         // Otherwise, return list.
//        }
//
//        return ResponseEntity.ok(response);
//    }

    @PostMapping
    public ResponseEntity<VideoGameResponse> addNewGame(@RequestBody VideoGameCreateRequest gameCreateRequest) {
        VideoGame videoGame = new VideoGame(gameCreateRequest.getTitle(),
                gameCreateRequest.getDeveloper(),
                gameCreateRequest.getGenre(),
                randomUUID());

        catalogService.addNewGame(videoGame);

        VideoGameResponse gameResponse = createVideoGameResponse(videoGame);

        return ResponseEntity.created(URI.create("/games/" + gameResponse.getId())).body(gameResponse);         // Created a new endpoint for specific game instance.

    }

    @PutMapping
    public ResponseEntity<VideoGameResponse> updateGame(@RequestBody VideoGameUpdateRequest gameUpdateRequest) {
        VideoGame videoGame = new VideoGame((gameUpdateRequest.getGameTitle()),
                gameUpdateRequest.getDeveloper(),
                gameUpdateRequest.getGenre(),
                gameUpdateRequest.getId());

//        catalogService.updateGame(videoGame);                                  TODO: <---- Uncomment this once the updateGame() method has been created in VideoGameCatalogService.

        VideoGameResponse gameResponse = createVideoGameResponse(videoGame);

        return ResponseEntity.ok(gameResponse);
    }

    @DeleteMapping("/{title}")
    public ResponseEntity deleteConcertByTitle(@PathVariable("title") String title) {
//        catalogService.deleteGame(title);                                     TODO: <---- Uncomment this once the deleteGame() method has been created in VideoGameCatalogService.
        return ResponseEntity.status(204).build();
    }
>>>>>>> origin/juvisbranch

}
