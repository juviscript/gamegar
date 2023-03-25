package com.kenzie.appserver.controller;





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


}
