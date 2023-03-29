package com.kenzie.appserver.controller;

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

//    @GetMapping("/{userId}")
//    public ResponseEntity<UserResponse> searchByUserId(@PathVariable("userId") String userId) {
////        User user = userService.findUserById(userId);
//
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }                                                         TODO: Uncomment this line once findByUserId is created in UserService class.
//
//        UserResponse userResponse = createUserResponse(user);
//
//        return ResponseEntity.ok(userResponse);
//    }

//    @GetMapping
//    public ResponseEntity<List<UserResponse>> getAllUsers() {
////        List<User> users = userService.findAllUsers();
//
//        if (users == null || users.isEmpty()) {
//            return ResponseEntity.status(204).build();
//        }
//                                                                  TODO: Uncomment this once findAllUsers() is created in UserService class.
//        List<UserResponse> response = new ArrayList<>();
//        for (User user : users) {
//            response.add(this.createUserResponse(user)):
//        }
//
//        return ResponseEntity.ok(response);
//    }

    @PostMapping
    public ResponseEntity<UserResponse> addNewUser(@RequestBody UserCreateRequest userCreateRequest) {
        User user = new User(randomUUID().toString(),
                userCreateRequest.getName(),
                userCreateRequest.getEmail(),
                userCreateRequest.getUsername(),
                userCreateRequest.getBirthday()
                );

        UserResponse userResponse = createUserResponse(user);

//        return ResponseEntity.created(URI.create("/users/" + userResponse.getId())).body(userResponse);       TODO: Uncomment this line once findById() is created in UserService class.
        return ResponseEntity.ok(userResponse);                                                              // TODO: Also delete this line once that is created!
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        User user = new User((userUpdateRequest.getUserId()),
                userUpdateRequest.getName(),
                userUpdateRequest.getEmail(),
                userUpdateRequest.getUsername(),
                userUpdateRequest.getBirthday());

//        userService.updateUser(user);                                  TODO: <---- Uncomment this once the updateUser() method has been created in UserService.

        UserResponse userResponse = createUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteConcertByTitle(@PathVariable("userId") String userId) {
//        userService.deleteUser(user);                                     TODO: <---- Uncomment this once the deleteUser() method has been created in UserService.
        return ResponseEntity.status(204).build();
    }


}
