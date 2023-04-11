//package com.kenzie.appserver.controller;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.kenzie.appserver.IntegrationTest;
//import com.kenzie.appserver.controller.model.CatalogCreateRequest;
//import com.kenzie.appserver.controller.model.CatalogUpdateRequest;
//import com.kenzie.appserver.controller.model.UserCreateRequest;
//import com.kenzie.appserver.service.CatalogService;
//import com.kenzie.appserver.service.UserService;
//import com.kenzie.appserver.service.model.Game;
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
//    CatalogService catalogService;
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
//        String description = mockNeat.strings().valStr();
//        String country = mockNeat.strings().valStr();
//        List platforms = new LinkedList<>();
//        List tags = new LinkedList();
//
//
//       Game videoGame = new Game(id, title, developer, genre, year,description,country,platforms,
//               tags);
//        Game persistedGame = catalogService.addNewGame(videoGame);
//
//        mapper.registerModule(new JavaTimeModule());
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
//    }
//
//    @Test
//    public void getGame_GameDoesNotExist() throws Exception {
//        // GIVEN
//        String id = UUID.randomUUID().toString();
//        // WHEN
//        mvc.perform(get("/games/{id}", id)
//                        .accept(MediaType.APPLICATION_JSON))
//                // THEN
//                .andExpect(status().isNotFound());
//    }
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
//        CatalogCreateRequest videoGameCreateRequest = new CatalogCreateRequest();
//        videoGameCreateRequest.setTitle(title);
//        videoGameCreateRequest.setDeveloper(developer);
//        videoGameCreateRequest.setGenre(genre);
//        videoGameCreateRequest.setYear(year);
//        videoGameCreateRequest.setDescription(description);
//        videoGameCreateRequest.setCountry(country);
//        videoGameCreateRequest.setPlatforms(platforms);
//        videoGameCreateRequest.setTags(tags);
//
//        mapper.registerModule(new JavaTimeModule());
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
//                .andExpect(jsonPath("description")
//                        .value(is(description)))
//                .andExpect(jsonPath("country")
//                        .value(is(country)))
//                .andExpect(jsonPath("platforms")
//                        .value(is(platforms)))
//                .andExpect(jsonPath("tags")
//                        .value(is(tags)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void updateGame_PutSuccessful() throws Exception {
//        // GIVEN
//        String id = UUID.randomUUID().toString();
//        String title = mockNeat.strings().valStr();
//        String developer = mockNeat.strings().valStr();
//        String genre = mockNeat.strings().valStr();
//        Integer year = 2000;
//        String description = mockNeat.strings().valStr();
//        String country = mockNeat.strings().valStr();
//        List platforms = new LinkedList<>();
//        List tags = new LinkedList();
//
//        Game game = new Game(id, title, developer, genre, year, description, country, platforms,
//                tags);
//        Game persistedGame = catalogService.addNewGame(game);
//
//        String newTitle = mockNeat.strings().valStr();
//        String newGenre = mockNeat.strings().valStr();
//
//        CatalogUpdateRequest videoGameUpdateRequest = new CatalogUpdateRequest();
//        videoGameUpdateRequest.setId(id);
//        videoGameUpdateRequest.setTitle(newTitle);
//        videoGameUpdateRequest.setDeveloper(developer);
//        videoGameUpdateRequest.setGenre(newGenre);
//        videoGameUpdateRequest.setYear(year);
//        videoGameUpdateRequest.setDescription(description);
//        videoGameUpdateRequest.setCountry(country);
//        videoGameUpdateRequest.setPlatforms(platforms);
//        videoGameUpdateRequest.setTags(tags);
//
//        mapper.registerModule(new JavaTimeModule());
//
//        // WHEN
//        mvc.perform(put("/games")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(videoGameUpdateRequest)))
//                // THEN
//                .andExpect(jsonPath("id")
//                        .exists())
//                .andExpect(jsonPath("title")
//                        .value(is(newTitle)))
//                .andExpect(jsonPath("developer")
//                        .value(is(developer)))
//                .andExpect(jsonPath("genre")
//                        .value(is(newGenre)))
//                .andExpect(jsonPath("year")
//                        .value(is(year)))
//                .andExpect(jsonPath("description")
//                        .value(is(description)))
//                .andExpect(jsonPath("country")
//                        .value(is(country)))
//                .andExpect(jsonPath("platforms")
//                        .value(is(platforms)))
//                .andExpect(jsonPath("tags")
//                        .value(is(tags)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteGame_DeleteSuccessful() throws Exception {
//        // GIVEN
//        String id = UUID.randomUUID().toString();
//        String title = mockNeat.strings().valStr();
//        String developer = mockNeat.strings().valStr();
//        String genre = mockNeat.strings().valStr();
//        Integer year = 2000;
//        String description = mockNeat.strings().valStr();
//        String country = mockNeat.strings().valStr();
//        List platforms = new LinkedList<>();
//        List tags = new LinkedList();
//
//        Game game = new Game(id, title, developer, genre, year, description,country, platforms,
//                tags);
//        Game persistedGame = catalogService.addNewGame(game);
//
//        // WHEN
//        mvc.perform(delete("/games/{id}", persistedGame.getId())
//                        .accept(MediaType.APPLICATION_JSON))
//                // THEN
//                .andExpect(status().isNoContent());
//        assertThat(catalogService.findByGameId(id)).isNull();
//    }
//}
//
//
//
