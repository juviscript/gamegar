package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.config.CacheStoreUser;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.VideoGameCatalogRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.repositories.model.VideoGameCatalogRecord;
import com.kenzie.appserver.service.model.User;
import com.kenzie.appserver.service.model.VideoGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.LinkedList;
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
                "pablito6789", LocalDate.of(1990,2,5)));

        userService.deleteUserById(userId);

        verify(cacheStoreUser).evict(record.getUserId());

        verify(userRepository, times(0)).deleteById(userId);
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
        record.setBirthday(LocalDate.of(2003,5,10));

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
                LocalDate.of(1980,8,25));

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
                LocalDate.of(1980,8,25));

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

}
