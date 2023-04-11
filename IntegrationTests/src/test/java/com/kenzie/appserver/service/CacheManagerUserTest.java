//package com.kenzie.appserver.service;
//
//import com.kenzie.appserver.IntegrationTest;
//import com.kenzie.appserver.config.CacheStoreUser;
//import com.kenzie.appserver.service.model.User;
//import net.andreinc.mockneat.MockNeat;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//
//import java.time.LocalDate;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@IntegrationTest
//public class CacheManagerUserTest {
//
//    @Autowired
//    CacheManager cacheManager;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    private CacheStoreUser Cache;
//
//    private final MockNeat mockNeat = MockNeat.threadLocal();
//
//    @Test
//    public void concertCache_InsertIntoCache() throws Exception {
//        String userId = UUID.randomUUID().toString();
//        String userName = mockNeat.strings().valStr();
//        String userEmail = "email@email.com";
//        String username = "username";
//        String birthday = LocalDate.now().toString();
//
//        User user = new User(userId,userName,userEmail,username,birthday);
//        userService.addNewUser(user);
//        userService.findUserById(userId);
//
//        User userFromCache = Cache.get(user.getUserId());
//
//        assertThat(userFromCache).isNotNull();
//        assertThat(userFromCache.getUserId()).isEqualTo(userId);
//    }
//
//    @Test
//    public void userCacheUpdate_EvictFromCache() throws Exception {
//        String userId = UUID.randomUUID().toString();
//        String userName = mockNeat.strings().valStr();
//        String userEmail = mockNeat.strings().valStr();
//        String username = mockNeat.strings().valStr();
//        String birthday = LocalDate.now().toString();
//
//        User user = new User(userId,userName,userEmail,username,birthday);
//        userService.addNewUser(user);
//        userService.findUserById(userId);
//
//        User userFromCache = Cache.get(user.getUserId());
//
//        userService.updateUser(user);
//
//        User userFromCacheAfterUpdate = Cache.get(user.getUserId());
//
//        assertThat(userFromCache).isNotNull();
//        assertThat(userFromCache.getUserId()).isEqualTo(userId);
//        assertThat(userFromCacheAfterUpdate).isNull();
//    }
//}