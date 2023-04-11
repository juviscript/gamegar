package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStoreUser;
import com.kenzie.appserver.controller.CatalogController;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.Game;
import com.kenzie.appserver.service.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public CacheStoreUser cache;

    @Autowired
    public UserService(UserRepository userRepository, CacheStoreUser cache) {
        this.userRepository = userRepository;
        this.cache = cache;
    }

    public User findUserById (String id) {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        return userRepository
=======
=======
>>>>>>> c1bcad1 (nojdijsjeoij)
=======

>>>>>>> 21f11de (Nicole might have made it somewhere)
        User cachedUser = cache.get(id);
        if (cachedUser != null) {
            return cachedUser;
        }

<<<<<<< HEAD
        User userFromService = userRepository
<<<<<<< HEAD
>>>>>>> 8c6347a (Added CacheManagerVideoGame)
=======
=======
        return userRepository
>>>>>>> 34de5ae (nicole stuff)
>>>>>>> c1bcad1 (nojdijsjeoij)
=======
        User userFromService = userRepository;
        return userRepository
>>>>>>> 21f11de (Nicole might have made it somewhere)
                .findById(id)
                .map(users -> new User(users.getUserId(),
                        users.getName(),
                        users.getEmail(),
                        users.getUsername(),
                        users.getBirthday(),
                        users.getFavoriteGame(),
                        users.getOwnGames()))
                .orElse(null);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> c1bcad1 (nojdijsjeoij)
=======

>>>>>>> 21f11de (Nicole might have made it somewhere)

        if (userFromService != null) {
            cache.add(userFromService.getUserId(), userFromService);
        }

        return userFromService;
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 8c6347a (Added CacheManagerVideoGame)
=======
=======
>>>>>>> 34de5ae (nicole stuff)
>>>>>>> c1bcad1 (nojdijsjeoij)
=======

>>>>>>> 21f11de (Nicole might have made it somewhere)
    }

    public User findUserByName (String names) {

        return userRepository
                .findById(names)
                .map(users -> new User(users.getName(),
                        users.getUserId(),
                        users.getEmail(),
                        users.getUsername(),
                        users.getBirthday(),
                        users.getFavoriteGame(),
                        users.getOwnGames()))
                .orElse(null);
    }
    public User findUserByEmail (String email) {

        return userRepository
                .findById(email)
                .map(users -> new User(users.getUserId(),
                        users.getName(),
                        users.getEmail(),
                        users.getUsername(),
                        users.getBirthday(),
                        users.getFavoriteGame(),
                        users.getOwnGames()))
                .orElse(null);
    }

    public User addNewUser(User user){
        UserRecord userRecord = new UserRecord();

        userRecord.setUserId(user.getUserId());
        userRecord.setName(user.getName());
        userRecord.setUsername(user.getUsername());
        userRecord.setEmail(user.getEmail());
        userRecord.setBirthday(user.getBirthday());
        userRecord.setFavoriteGame(user.getFavoriteGames());
        userRecord.setOwnGames(user.getOwnGames());
        userRepository.save(userRecord);

        if (userRecord != null) {
            cache.add(userRecord.getUserId(), user);
        }
        return user;
    }


    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();

        Iterable<UserRecord> userIterator = userRepository.findAll();
        for(UserRecord record : userIterator) {
            users.add(new User (record.getUserId(),
                    record.getName(),
                    record.getEmail(),
                    record.getUsername(),
                    record.getBirthday(),
                    record.getFavoriteGame(),
                    record.getOwnGames()));
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
            userRecord.setFavoriteGame(user.getFavoriteGames());
            userRecord.setFavoriteGame(user.getOwnGames());
            userRepository.save(userRecord);
        }

        User userFromCache = null;
        userFromCache = cache.get(user.getUserId());
        if(userFromCache != null){
            cache.evict(userFromCache.getUserId());
        }

    }

    public void deleteUserById(String userID){
        userRepository.deleteById(userID);
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 025556f (noidsoijeif)
    public List<VideoGame> getFavoriteGames(String userId) {
        User user = cache.get(userId);
        if (user != null) {
            return user.getFavoriteGames();
        }
        return new ArrayList<>();
<<<<<<< HEAD
    }

    public boolean addFavoriteGame(String userId, VideoGame game) {
        User user = cache.get(userId);
        if (user != null) {
            return user.addFavoriteGame(game);
=======
    public List<VideoGame> getFavoriteGames() {
        return favoriteGames;
    }

    public boolean addFavoriteGame(VideoGame game) {
        if (!favoriteGames.contains(game)) {
            return favoriteGames.add(game);
>>>>>>> c1bcad1 (nojdijsjeoij)
=======
    }

    public boolean addFavoriteGame(String userId, VideoGame game) {
        User user = cache.get(userId);
        if (user != null) {
            return user.addFavoriteGame(game);
>>>>>>> 025556f (noidsoijeif)
        }
        return false;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 025556f (noidsoijeif)
    public boolean removeFavoriteGame(String userId, VideoGame game) {
        User user = cache.get(userId);
        if (user != null) {
            return user.removeFavoriteGame(game);
<<<<<<< HEAD
=======
    public boolean removeFavoriteGame(VideoGame game) {
        return favoriteGames.remove(game);
    }
    public List<VideoGame> getOwnGames() {
        return ownGames;
    }

    public boolean addOwnGame(VideoGame game) {
        if (!ownGames.contains(game)) {
            return ownGames.add(game);
>>>>>>> c1bcad1 (nojdijsjeoij)
=======
>>>>>>> 025556f (noidsoijeif)
        }
        return false;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 025556f (noidsoijeif)
    public List<VideoGame> getOwnGames(String userId) {
        User user = cache.get(userId);
        if (user != null) {
            return user.getOwnedGames();
        }
        return new ArrayList<>();
<<<<<<< HEAD
    }
    public boolean addOwnGame(String userId, VideoGame game) {
        User user = cache.get(userId);
        if (user != null) {
            return user.addOwnedGame(game);
        }
        return false;
    }
}
=======
    public boolean removeOwnGame(VideoGame game) {
        return ownGames.remove(game);
    }
}



>>>>>>> c1bcad1 (nojdijsjeoij)
=======
    }
    public boolean addOwnGame(String userId, VideoGame game) {
        User user = cache.get(userId);
        if (user != null) {
            return user.addOwnedGame(game);
        }
        return false;
    }
}
>>>>>>> 025556f (noidsoijeif)
=======

    public void addFavoriteGame(User userId, Game id) {
        User user = findUserById(userId.getUserId());
        if (user == null) {
            System.out.println("User not found");
            return;
        }

        // Check if the user already has the game in their list of favorite games
        for (List favoriteGame : user.getFavoriteGames()) {
            if (favoriteGame.contains(id)) {
                System.out.println("Game is already a favorite of this user");
                return;
            }
        }

        // Add the game to the user's list of favorite games
        user.getFavoriteGames().add((List) id);

        // Print a message indicating that the game has been added to the user's list of favorites
        System.out.println("Game added to user's list of favorites");
    }
    public void deleteFavoriteGame(User userId, Game id) {
        User user = findUserById(userId.getUserId());
        if (user == null) {
            System.out.println("User not found");
            return;
        }

        // Check if the user already has the game in their list of favorite games
        for (List favoriteGame : user.getFavoriteGames()) {
            if (favoriteGame.contains(id)) {
                user.getFavoriteGames().remove((List) id);
            }
        }

        // Print a message indicating that the game has been added to the user's list of favorites
        System.out.println("Game remove from user's list of favorites");
    }
    public void addOwnGame(User userId, Game id){
        User user = findUserById(userId.getUserId());
        if (user == null){
            System.out.println("User not found");
            return;
        } for (List ownGame : user.getOwnGames()) {
            if (ownGame.contains(id)) {
                System.out.println("Game is already a favorite of this user");
                return;
            }
        }

        // Add the game to the user's list of favorite games
        user.getOwnGames().add((List) id);

        // Print a message indicating that the game has been added to the user's list of favorites
        System.out.println("Game added to user's list of Own");
    }
    public void deleteOwnGame(User userId, Game id) {
        User user = findUserById(userId.getUserId());
        if (user == null) {
            System.out.println("User not found");
            return;
        }

        // Check if the user already has the game in their list of favorite games
        for (List ownGame : user.getOwnGames()) {
            if (ownGame.contains(id)) {
                user.getOwnGames().remove((List) id);
            }
        }

        // Print a message indicating that the game has been added to the user's list of favorites
        System.out.println("Game remove from user's list of own");
    }



}

}
>>>>>>> 21f11de (Nicole might have made it somewhere)
