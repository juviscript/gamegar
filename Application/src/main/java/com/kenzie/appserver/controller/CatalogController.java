package com.kenzie.appserver.controller;





import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
import com.kenzie.appserver.service.VideoGameCatalogService;
import com.kenzie.appserver.service.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//TODO need to make Response and Request
@RestController
@RequestMapping("/games")           // This is the API extension name (ex: localhost:8000/games) will pull this up.

public class CatalogController {
    private VideoGameCatalogService catalogService;

    CatalogController(VideoGameCatalogService catalogService) {
        this.catalogService = catalogService;
    }
//
//    @GetMapping("/games/all")
//    public ResponseEntity<>
}
