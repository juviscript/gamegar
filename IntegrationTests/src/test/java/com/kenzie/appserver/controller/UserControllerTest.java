package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.UserCreateRequest;
import com.kenzie.appserver.controller.model.UserUpdateRequest;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserService userService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    //Does not pass

    @Test
    public void getUser_UserExists() throws Exception {
        // GIVEN
        String userId = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String email = mockNeat.strings().valStr();
        String username = mockNeat.strings().valStr();
        String birthday = LocalDate.now().toString();

        User user = new User(userId,name,email,username,birthday);
        User persistedUser = userService.addNewUser(user);

        // WHEN
        mvc.perform(get("/users/{userId}", persistedUser.getUserId())
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(jsonPath("userId")
                        .value(is(userId)))
                .andExpect(jsonPath("name")
                        .value(is(name)))
                .andExpect(jsonPath("email")
                        .value(is(email)))
                .andExpect(jsonPath("username")
                        .value(is(username)))
                .andExpect(jsonPath("birthday")
                        .value(is(birthday)))
                .andExpect(status().isOk());
    }

    // Pass
    @Test
    public void getUser_UserDoesNotExist() throws Exception {
        // GIVEN
        String id = UUID.randomUUID().toString();
        // WHEN
        mvc.perform(get("/users/{userId}", id)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isNotFound());
    }
    //Pass
    @Test
    public void createUser_CreateSuccessful() throws Exception {
        // GIVEN
        String userId = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String email = mockNeat.strings().valStr();
        String username = mockNeat.strings().valStr();
        String birthday = LocalDate.now().toString();

        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setName(name);
        userCreateRequest.setEmail(email);
        userCreateRequest.setUsername(username);
        userCreateRequest.setBirthday(birthday);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userCreateRequest)))
                // THEN
                .andExpect(jsonPath("userId")
                        .exists())
                .andExpect(jsonPath("name")
                        .value(is(name)))
                .andExpect(jsonPath("email")
                        .value(is(email)))
                .andExpect(jsonPath("username")
                        .value(is(username)))
                .andExpect(jsonPath("birthday")
                        .value(is(birthday)))
                .andExpect(status().isCreated());
    }
    //Pass
    @Test
    public void updateGame_PutSuccessful() throws Exception {
        // GIVEN
        String userId = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String email = mockNeat.strings().valStr();
        String username = mockNeat.strings().valStr();
        String birthday = LocalDate.now().toString();

        User user = new User(userId,name,email,username,birthday);
        User persistedUser = userService.addNewUser(user);

        String newEmail = mockNeat.strings().valStr();
        String newName = mockNeat.strings().valStr();

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setUserId(userId);
        userUpdateRequest.setName(newName);
        userUpdateRequest.setEmail(newEmail);
        userUpdateRequest.setUsername(username);
        userUpdateRequest.setBirthday(birthday);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(put("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userUpdateRequest)))
                // THEN
                .andExpect(jsonPath("userId")
                        .exists())
                .andExpect(jsonPath("name")
                        .value(is(newName)))
                .andExpect(jsonPath("email")
                        .value(is(newEmail)))
                .andExpect(jsonPath("username")
                        .value(is(username)))
                .andExpect(jsonPath("birthday")
                        .value(is(birthday)))
                .andExpect(status().isOk());
    }

    //Does not pass

    @Test
    public void deleteUser_DeleteSuccessful() throws Exception {
        // GIVEN
        String userId = UUID.randomUUID().toString();
        String name = mockNeat.strings().valStr();
        String email = mockNeat.strings().valStr();
        String username = mockNeat.strings().valStr();
        String birthday = LocalDate.now().toString();

        User user = new User(userId,name,email,username,birthday);
        User persistedUser = userService.addNewUser(user);

       // WHEN
        mvc.perform(delete("/users/{userId}", persistedUser.getUserId())
                        .accept(MediaType.APPLICATION_JSON))

                // THEN
                .andExpect(status().isNoContent());
        assertThat(userService.findUserById(userId)).isNull();
    }
}

