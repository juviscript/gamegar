package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.config.CacheStoreUser;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.User;
import com.kenzie.appserver.service.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public CacheStoreUser cache;
    public List<VideoGame> favoriteGames;
    public List<VideoGame> ownGames;
    @Autowired
    public UserService(UserRepository userRepository, CacheStoreUser cache) {
        this.userRepository = userRepository;
        this.cache = cache;
    }

    public User findUserById (String id) {

        return userRepository
                .findById(id)
                .map(users -> new User(users.getUserId(),
                        users.getName(),
                        users.getEmail(),
                        users.getUsername(),
                        users.getBirthday()))
                .orElse(null);
    }

    public User findUserByName (String names) {

        return userRepository
                .findById(names)
                .map(users -> new User(users.getName(),
                        users.getUserId(),
                        users.getEmail(),
                        users.getUsername(),
                        users.getBirthday()))
                .orElse(null);
    }
    public User findUserByEmail (String email) {

        return userRepository
                .findById(email)
                .map(users -> new User(users.getUserId(),
                        users.getName(),
                        users.getEmail(),
                        users.getUsername(),
                        users.getBirthday()))
                .orElse(null);
    }

    public User addNewUser(User user){
        UserRecord userRecord = new UserRecord();

        userRecord.setUserId(user.getUserId());
        userRecord.setUsername(user.getUsername());
        userRecord.setName(user.getName());
        userRecord.setEmail(user.getEmail());
        userRecord.setBirthday(user.getBirthday());
        userRepository.save(userRecord);
        return user;
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();

        Iterable<UserRecord> userIterator = userRepository.findAll();
        for(UserRecord record : userIterator) {
            users.add(new User (record.getUserId(),
                    record.getUsername(),
                    record.getName(),
                    record.getEmail(),
                    record.getBirthday()));
        }
        return users;
    }

    public void updateUser (User user) {
        if (userRepository.existsById(user.getUserId())) {
            UserRecord userRecord = new UserRecord();
            userRecord.setUserId(user.getUserId());
            userRecord.setUsername(user.getUsername());
            userRecord.setName(user.getName());
            userRecord.setEmail(user.getEmail());
            userRecord.setBirthday(user.getBirthday());
            userRepository.save(userRecord);
        }

        User userFromCache = null;
        userFromCache = cache.get(user.getUserId());
        if(userFromCache != null){
            cache.evict(userFromCache.getUserId());
        }

    }

    public void deleteUserById(String userID){
        User user = null;
        if (userID != null) {
            user  = cache.get(userID);
        } else {
            throw new IllegalArgumentException("User ID not valid.");
        }
        if (user  != null) {
            cache.evict(user.getUserId());
        }
    }
    public List<VideoGame> getFavoriteGames() {
        return favoriteGames;
    }

    public boolean addFavoriteGame(VideoGame game) {
        if (!favoriteGames.contains(game)) {
            return favoriteGames.add(game);
        }
        return false;
    }

    public boolean removeFavoriteGame(VideoGame game) {
        return favoriteGames.remove(game);
    }
    public List<VideoGame> getOwnGames() {
        return ownGames;
    }

    public boolean addOwnGame(VideoGame game) {
        if (!ownGames.contains(game)) {
            return ownGames.add(game);
        }
        return false;
    }

    public boolean removeOwnGame(VideoGame game) {
        return ownGames.remove(game);
    }
}



