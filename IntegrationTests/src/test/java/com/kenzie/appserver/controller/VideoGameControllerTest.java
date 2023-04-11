//package com.kenzie.appserver.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.kenzie.appserver.IntegrationTest;
//import com.kenzie.appserver.controller.model.UserCreateRequest;
//import com.kenzie.appserver.controller.model.VideoGameCreateRequest;
//import com.kenzie.appserver.controller.model.VideoGameUpdateRequest;
//import com.kenzie.appserver.service.UserService;
//import com.kenzie.appserver.service.VideoGameCatalogService;
//import com.kenzie.appserver.service.model.VideoGame;
//import net.andreinc.mockneat.MockNeat;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@IntegrationTest
//public class VideoGameControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    VideoGameCatalogService videoGameCatalogService;
//
//    private final MockNeat mockNeat = MockNeat.threadLocal();
//
//    private final ObjectMapper mapper = new ObjectMapper();
//    //Does not pass
//    @Test
//    public void getGame_GameExists() throws Exception {
//        // GIVEN
//        String id = UUID.randomUUID().toString();
//        String title = mockNeat.strings().valStr();
//        String developer = mockNeat.strings().valStr();
//        String genre = mockNeat.strings().valStr();
//        Integer year = 2000;
//        List platforms = new LinkedList<>();
//        List tags = new LinkedList();
//        String description = mockNeat.strings().valStr();
//        String country = mockNeat.strings().valStr();
//
//        VideoGame videoGame = new VideoGame(id, title, developer, genre, year,platforms,
//                tags,description,country);
//        VideoGame persistedGame = videoGameCatalogService.addNewGame(videoGame);
//
////        mapper.registerModule(new JavaTimeModule());
//
//        // WHEN
//        mvc.perform(get("/games/{id}", persistedGame.getId())
//                        .accept(MediaType.APPLICATION_JSON))
//                // THEN
//                .andExpect(jsonPath("id")
//                        .value(is(id)))
//                .andExpect(jsonPath("title")
//                        .value(is(title)))
//                .andExpect(jsonPath("developer")
//                        .value(is(developer)))
//                .andExpect(jsonPath("genre")
//                        .value(is(genre)))
//                .andExpect(jsonPath("year")
//                        .value(is(year)))
//                .andExpect(jsonPath("platforms").value(is(platforms)))
//                .andExpect(jsonPath("tags")
//                        .value(is(tags)))
//                .andExpect(jsonPath("description")
//                        .value(is(description)))
//                .andExpect(jsonPath("country")
//                        .value(is(country)))
//                .andExpect(status().isOk());
////        String id = UUID.randomUUID().toString();
////        String title = mockNeat.strings().valStr();
////        String developer = mockNeat.strings().valStr();
////        String genre = mockNeat.strings().valStr();
////        Integer year = 2000;
////        List platforms = new LinkedList<>();
////        List tags = new LinkedList();
////        String description = mockNeat.strings().valStr();
////        String country = mockNeat.strings().valStr();
////
////        VideoGameCreateRequest videoGameCreateRequest = new VideoGameCreateRequest();
////        videoGameCreateRequest.setTitle(title);
////        videoGameCreateRequest.setDeveloper(developer);
////        videoGameCreateRequest.setGenre(genre);
////        videoGameCreateRequest.setYear(year);
////        videoGameCreateRequest.setPlatforms(platforms);
////        videoGameCreateRequest.setTags(tags);
////        videoGameCreateRequest.setDescription(description);
////        videoGameCreateRequest.setCountry(country);
////
////        //mapper.registerModule(new JavaTimeModule());
////
////        // WHEN
////        mvc.perform(post("/games")
////                        .accept(MediaType.APPLICATION_JSON)
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(mapper.writeValueAsString(videoGameCreateRequest)))
////
////                // THEN
////                .andExpect(jsonPath("id")
////                        .exists())
////                .andExpect(jsonPath("title")
////                        .value(is(title)))
////                .andExpect(jsonPath("developer")
////                        .value(is(developer)))
////                .andExpect(jsonPath("genre")
////                        .value(is(genre)))
////                .andExpect(jsonPath("year")
////                        .value(is(year)))
////                .andExpect(jsonPath("platforms")
////                        .value(is(platforms)))
////                .andExpect(jsonPath("tags")
////                        .value(is(tags)))
////                .andExpect(jsonPath("description")
////                        .value(is(description)))
////                .andExpect(jsonPath("country")
////                        .value(is(country)))
////                .andExpect(status().isCreated());
////
////        mvc.perform(get("/games", id)
////                        .accept(MediaType.APPLICATION_JSON))
////                .andExpect(jsonPath("id")
////                       .exists())
////                .andExpect(jsonPath("title")
////                        .value(is(title)))
////                .andExpect(jsonPath("developer")
////                        .value(is(developer)))
////                .andExpect(jsonPath("genre")
////                        .value(is(genre)))
////               .andExpect(jsonPath("year")
////                        .value(is(year)))
////                .andExpect(jsonPath("platforms")
////                        .value(is(platforms)))
////                .andExpect(jsonPath("tags")
////                        .value(is(tags)))
////                .andExpect(jsonPath("description")
////                       .value(is(description)))
////               .andExpect(jsonPath("country")
////                       .value(is(country)))
////                .andExpect(status().is2xxSuccessful());
//    }
//
//    //Does not pass
////    @Test
////    public void getGame_GameDoesNotExist() throws Exception {
////        // GIVEN
////        String id = UUID.randomUUID().toString();
////        // WHEN
////        mvc.perform(get("/games/{gameId}", id)
////                        .accept(MediaType.APPLICATION_JSON))
////                // THEN
////                .andExpect(status().isNotFound());
////    }
//    //Pass
//    @Test
//    public void createGame_CreateSuccessful() throws Exception {
//        // GIVEN
//        String id = UUID.randomUUID().toString();
//        String title = mockNeat.strings().valStr();
//        String developer = mockNeat.strings().valStr();
//        String genre = mockNeat.strings().valStr();
//        Integer year = 2000;
//        List platforms = new LinkedList();
//        List tags = new LinkedList();
//        String description = mockNeat.strings().valStr();
//        String country = mockNeat.strings().valStr();
//
//        VideoGameCreateRequest videoGameCreateRequest = new VideoGameCreateRequest();
//        videoGameCreateRequest.setTitle(title);
//        videoGameCreateRequest.setDeveloper(developer);
//        videoGameCreateRequest.setGenre(genre);
//        videoGameCreateRequest.setYear(year);
//        videoGameCreateRequest.setPlatforms(platforms);
//        videoGameCreateRequest.setTags(tags);
//        videoGameCreateRequest.setDescription(description);
//        videoGameCreateRequest.setCountry(country);
//
//        //mapper.registerModule(new JavaTimeModule());
//
//        // WHEN
//        mvc.perform(post("/games")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(videoGameCreateRequest)))
//                // THEN
//                .andExpect(jsonPath("id")
//                        .exists())
//                .andExpect(jsonPath("title")
//                        .value(is(title)))
//                .andExpect(jsonPath("developer")
//                        .value(is(developer)))
//                .andExpect(jsonPath("genre")
//                        .value(is(genre)))
//                .andExpect(jsonPath("year")
//                        .value(is(year)))
//                .andExpect(jsonPath("platforms")
//                        .value(is(platforms)))
//                .andExpect(jsonPath("tags")
//                        .value(is(tags)))
//                .andExpect(jsonPath("description")
//                        .value(is(description)))
//                .andExpect(jsonPath("country")
//                        .value(is(country)))
//                .andExpect(status().isCreated());
//    }
//    //Does not pass
////    @Test
////    public void updateGame_PutSuccessful() throws Exception {
////        // GIVEN
////        String id = UUID.randomUUID().toString();
////        String title = mockNeat.strings().valStr();
////        String developer = mockNeat.strings().valStr();
////        String genre = mockNeat.strings().valStr();
////        Integer year = 2000;
////        List platforms = new LinkedList<>();
////        List tags = new LinkedList();
////        String description = mockNeat.strings().valStr();
////        String country = mockNeat.strings().valStr();
////
////        VideoGame videoGame = new VideoGame(id, title, developer, genre, year,platforms,
////                tags,description,country);
////        VideoGame persistedGame = videoGameCatalogService.addNewGame(videoGame);
////
////        String newTitle = mockNeat.strings().valStr();
////        String newGenre = mockNeat.strings().valStr();
////
////        VideoGameUpdateRequest videoGameUpdateRequest = new VideoGameUpdateRequest();
////        videoGameUpdateRequest.setId(id);
////        videoGameUpdateRequest.setTitle(newTitle);
////        videoGameUpdateRequest.setDeveloper(developer);
////        videoGameUpdateRequest.setGenre(newGenre);
////        videoGameUpdateRequest.setYear(year);
////        videoGameUpdateRequest.setPlatforms(platforms);
////        videoGameUpdateRequest.setTags(tags);
////        videoGameUpdateRequest.setDescription(description);
////        videoGameUpdateRequest.setCountry(country);
////
////        mapper.registerModule(new JavaTimeModule());
////
////        // WHEN
////        mvc.perform(put("/games")
////                        .accept(MediaType.APPLICATION_JSON)
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(mapper.writeValueAsString(videoGameUpdateRequest)))
////                // THEN
////                .andExpect(jsonPath("id")
////                        .exists())
////                .andExpect(jsonPath("title")
////                        .value(is(newTitle)))
////                .andExpect(jsonPath("developer")
////                        .value(is(developer)))
////                .andExpect(jsonPath("genre")
////                        .value(is(newGenre)))
////                .andExpect(jsonPath("year")
////                        .value(is(year)))
////                .andExpect(jsonPath("platforms")
////                        .value(is(platforms)))
////                .andExpect(jsonPath("tags")
////                        .value(is(tags)))
////                .andExpect(jsonPath("description")
////                        .value(is(description)))
////                .andExpect(jsonPath("country")
////                        .value(is(country)))
////                .andExpect(status().isOk());
////    }
////    //Does not pass
////    @Test
////    public void deleteGame_DeleteSuccessful() throws Exception {
////        // GIVEN
////        String id = UUID.randomUUID().toString();
////        String title = mockNeat.strings().valStr();
////        String developer = mockNeat.strings().valStr();
////        String genre = mockNeat.strings().valStr();
////        Integer year = 2000;
////        List platforms = new LinkedList<>();
////        List tags = new LinkedList();
////        String description = mockNeat.strings().valStr();
////        String country = mockNeat.strings().valStr();
////
////        VideoGame videoGame = new VideoGame(id, title, developer, genre, year,platforms,
////                tags,description,country);
////        VideoGame persistedGame = videoGameCatalogService.addNewGame(videoGame);
////
////        // WHEN
////        mvc.perform(delete("/games/{gameId}", persistedGame.getId())
////                        .accept(MediaType.APPLICATION_JSON))
////                // THEN
////                .andExpect(status().isNoContent());
////        assertThat(videoGameCatalogService.findGameById(id)).isNull();
////    }
//}
//
//
//
