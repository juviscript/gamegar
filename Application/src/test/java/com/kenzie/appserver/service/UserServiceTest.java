package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStoreUser;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private CacheStoreUser cacheStoreUser;
    private UserService userService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        cacheStoreUser = mock(CacheStoreUser.class);
        userService = new UserService(userRepository, cacheStoreUser);
    }

    @Test
    void deleteUser_null_input() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.deleteUserById(null));
    }

    @Test
    void deleteUser() {
        String userId = "1";
        UserRecord record = new UserRecord();
        record.setUserId(userId);

        Mockito.when(userRepository.existsById(anyString())).thenReturn(true);
        Mockito.when(userRepository.findById(anyString())).thenReturn(Optional.of(record));

        when(cacheStoreUser.get(record.getUserId())).thenReturn(new User(userId, "Pablo", "pablo@gmail.com",
                "pablito6789", "birthday", favoriteGame, ownGames));

        userService.deleteUserById(userId);

        verify(cacheStoreUser).evict(record.getUserId());

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void userService_findByUserId_idIsNull() {
        // GIVEN
        UserRecord nullIdUser = new UserRecord();
        nullIdUser.setUserId(null);

        // WHEN
        when(userRepository.findById(nullIdUser.getUserId())).thenReturn(Optional.empty());
        User response = userService.findUserById(nullIdUser.getUserId());

        //THEN
        Assertions.assertNull(response);
    }

    @Test
    void findByUserId() {
        // GIVEN
        String userId = randomUUID().toString();

        UserRecord record = new UserRecord();
        record.setUserId(userId);
        record.setName("Rosa");
        record.setUsername("rositafresita12");
        record.setEmail("rosaloca@gmail.com");
        record.setBirthday("birthday");

        when(userRepository.findById(userId)).thenReturn(Optional.of(record));
        // WHEN
        User user = userService.findUserById(userId);

        // THEN
        Assertions.assertNotNull(user, "The user is returned");
        assertEquals(record.getUserId(), user.getUserId(), "The user id matches");
        assertEquals(record.getName(), user.getName(), "The user's name matches");
        assertEquals(record.getUsername(), user.getUsername(), "The username matches");
        assertEquals(record.getEmail(), user.getEmail(), "The user's email matches");
        assertEquals(record.getBirthday(), user.getBirthday(), "The user's birthday matches");
    }


    @Test
    void addNewUser() {
        // GIVEN
        String userId = randomUUID().toString();

        User user = new User(userId, "name","email","username",
                "birthday", favoriteGame, ownGames);

        ArgumentCaptor<UserRecord> userRecordCaptor = ArgumentCaptor.forClass(UserRecord.class);

        // WHEN
        User returnedUser = userService.addNewUser(user);

        // THEN
        Assertions.assertNotNull(returnedUser);

        verify(userRepository).save(userRecordCaptor.capture());

        UserRecord record = userRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The user record is returned");
        assertEquals(record.getUserId(), user.getUserId(), "The user id matches");
        assertEquals(record.getName(), user.getName(), "The user's name matches");
        assertEquals(record.getUsername(), user.getUsername(), "The username matches");
        assertEquals(record.getEmail(), user.getEmail(), "The user's email matches");
        assertEquals(record.getBirthday(), user.getBirthday(), "The user's birthday matches");
    }

    @Test
    public void updateUser_validUser_userUpdated() {

        String userId = randomUUID().toString();

        User user = new User(userId, "name","email","username",
                "birthday", favoriteGame, ownGames);

        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(user.getUserId());
        userRecord.setName(user.getName());
        userRecord.setEmail(user.getEmail());
        userRecord.setUsername(user.getUsername());
        userRecord.setBirthday(user.getBirthday());

        when(userRepository.existsById(user.getUserId())).thenReturn(true);

        ArgumentCaptor<UserRecord> argumentCaptor = ArgumentCaptor.forClass(UserRecord.class);
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(userRecord));
        when(userRepository.save(argumentCaptor.capture())).thenReturn(userRecord);
        when(cacheStoreUser.get(user.getUserId())).thenReturn(user);

        userService.updateUser(user);

        verify(cacheStoreUser).evict(user.getUserId());
        verify(userRepository).existsById(user.getUserId());
        verify(userRepository).save(argumentCaptor.capture());
        UserRecord capturedUserRecord = argumentCaptor.getValue();
        assertEquals(capturedUserRecord.getUserId(), userRecord.getUserId());
        assertEquals(capturedUserRecord.getName(), userRecord.getName());
        assertEquals(capturedUserRecord.getEmail(), userRecord.getEmail());
        assertEquals(capturedUserRecord.getUsername(), userRecord.getUsername());
        assertEquals(capturedUserRecord.getBirthday(), userRecord.getBirthday());

    }
    
    @Test
    public void findUserByName_returnsNull() {
        UserRecord nullIdUser = new UserRecord();
        nullIdUser.setName(null);

        when(userRepository.findById(nullIdUser.getName())).thenReturn(Optional.empty());
        User response = userService.findUserByName(nullIdUser.getName());

        Assertions.assertNull(response);
    }
    @Test
    public void findAllUsers() {
        UserRecord user1 = new UserRecord();
        user1.setUserId(randomUUID().toString());
        user1.setName("Rosa");
        user1.setUsername("rositafresita12");
        user1.setEmail("rosaloca@gmail.com");
        user1.setBirthday("birthday");

        UserRecord user2 = new UserRecord();
        user2.setUserId(randomUUID().toString());
        user2.setName("Franky");
        user2.setUsername("SuperFranky37");
        user2.setEmail("BF-37@gmail.com");
        user2.setBirthday("birthday");

        List<UserRecord> records = new ArrayList<>();
        records.add(user1);
        records.add(user2);
        when(userRepository.findAll()).thenReturn(records);

        List<User> usersList = userService.findAllUsers();

        Assertions.assertNotNull(usersList, "The user list is returned");
        Assertions.assertEquals(2, usersList.size(), "There are 2 users.");

        for(User user : usersList) {
            if(user.getUserId().equals(user1.getUserId())) {
                Assertions.assertEquals(user1.getName(), user.getName(), "The usersList name match");
                Assertions.assertEquals(user1.getEmail(), user.getEmail(), "The user emails match");
                Assertions.assertEquals(user1.getUsername(), user.getUsername(), "The usernames match");
                Assertions.assertEquals(user1.getBirthday(), user.getBirthday(), "The usersList birthdays match");
            } else if (user.getUserId().equals(user2.getUserId())) {
                Assertions.assertEquals(user2.getName(), user.getName(), "The usersList name match");
                Assertions.assertEquals(user2.getUsername(), user.getUsername(), "The usernames match");
                Assertions.assertEquals(user2.getEmail(), user.getEmail(), "The user emails match");
                Assertions.assertEquals(user2.getBirthday(), user.getBirthday(), "The usersList birthdays match");
            } else {
                Assertions.assertTrue(false, "User returned was not in the records!");
            }
        }
    }
}
