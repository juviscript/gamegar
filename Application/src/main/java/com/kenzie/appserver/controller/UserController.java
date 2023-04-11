


package com.kenzie.appserver.controller;
import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
import com.kenzie.appserver.service.VideoGameCatalogService;
import com.kenzie.appserver.controller.model.*;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    private UserResponse createUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId((user.getUserId()));
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());                     // This class converts a User class into a UserResponse class.
        userResponse.setUsername(user.getUsername());
        userResponse.setBirthday(user.getBirthday());
        return userResponse;
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserResponse> searchByUserId(@PathVariable("userId") String userId) {
        User user = userService.findUserById(userId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
<<<<<<< HEAD

        UserResponse userResponse = createUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponse> searchByName(@PathVariable("name") String name) {
        User user = userService.findUserByName(name);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserResponse userResponse = createUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> searchByEmail(@PathVariable("email") String email) {
        User user = userService.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
=======
>>>>>>> 025556f (noidsoijeif)

        UserResponse userResponse = createUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponse> searchByName(@PathVariable("name") String name) {
        User user = userService.findUserByName(name);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserResponse userResponse = createUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> searchByEmail(@PathVariable("email") String email) {
        User user = userService.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserResponse userResponse = createUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.findAllUsers();

        if (users == null || users.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<UserResponse> response = new ArrayList<>();
        for (User user : users) {
            response.add(this.createUserResponse(user));
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponse> addNewUser(@RequestBody UserCreateRequest userCreateRequest) {
        User user = new User(randomUUID().toString(),
                userCreateRequest.getName(),
                userCreateRequest.getEmail(),
                userCreateRequest.getUsername(),
                userCreateRequest.getBirthday()
        );
        userService.addNewUser(user);

        UserResponse userResponse = createUserResponse(user);

        return ResponseEntity.created(URI.create("/users/" + userResponse.getUserId())).body(userResponse);
        //return ResponseEntity.ok(userResponse);                                                              // TODO: Also delete this line once that is created!
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        User user = new User((userUpdateRequest.getUserId()),
                userUpdateRequest.getName(),
                userUpdateRequest.getEmail(),
                userUpdateRequest.getUsername(),
                userUpdateRequest.getBirthday());

        userService.updateUser(user);

        UserResponse userResponse = createUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{userId}")
<<<<<<< HEAD
<<<<<<< HEAD
    public ResponseEntity deleteConcertByTitle(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
=======
    public ResponseEntity deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);                                     
>>>>>>> e2cd2fa (Fixed tests and delete method in catalogservice)
=======
    public ResponseEntity deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);                                     
=======
    public ResponseEntity deleteConcertByTitle(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
>>>>>>> 50543db (nicole stuff that wont work right)
>>>>>>> 025556f (noidsoijeif)
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/favorite")
    public ResponseEntity<?> addFavoriteGame(@PathVariable("userId") String userId, @RequestBody CatalogController id) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (userService.addFavoriteGame(id) {
            userService.updateUser(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Game already in favorites.");
    }

    @DeleteMapping("/favorite/{favorite}")
    public ResponseEntity<?> removeFavoriteGame(@PathVariable("userId") String userId, @PathVariable("id") String id) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.findFavoriteGameById(id);
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        userService.removeFavoriteGame(id);
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/own")
    public ResponseEntity<?> addOwnGame(@PathVariable("userId") String userId, @RequestBody CatalogController id) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (userService.addOwnGame(id)) {
            userService.updateUser(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Game already in owned games.");
    }

    @DeleteMapping("/own/{own}")
    public ResponseEntity<?> removeOwnGame(@PathVariable("userId") String userId, @PathVariable("id") String id) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserService.findOwnGameById(id);
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        userService.removeFavoriteGame(id);
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }
}