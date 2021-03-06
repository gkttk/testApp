package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.UserDao;
import com.github.gkttk.testApp.api.UserDetailsDao;
import net.sf.ehcache.CacheManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.github.gkttk.testApp.config.DaoConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class UserDetailsDaoImplTest {

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testCacheUserDetails() {
        UserDetails userDetails1 = userDetailsDao.getUserDetails(1);
        UserDetails userDetails2 = userDetailsDao.getUserDetails(1);
        int size = CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("com.github.gkttk.testApp.UserDetails").getSize();
        Assertions.assertTrue(size > 0);
    }

    @Test
    public void testGetUserDetails() {
        User user = userDao.getUserByLogin("admin");
        int userId = user.getId();
        UserDetails userDetailsFromDB = userDetailsDao.getUserDetails(userId);
        Assertions.assertNotNull(userDetailsFromDB);
        Assertions.assertAll(
                () -> Assertions.assertEquals(userDetailsFromDB.getName(), user.getuDetails().getName()),
                () -> Assertions.assertEquals(userDetailsFromDB.getSurname(), user.getuDetails().getSurname()),
                () -> Assertions.assertEquals(userDetailsFromDB.getAge(), user.getuDetails().getAge()));
    }

    @Test
    public void updateUserDetailsHibernateTest() {
        User user = new User("TestLogin", "TestPassword", "test@gmail.ru");
        UserDetails userDetails = new UserDetails(null, null, null, null, user);
        user.setuDetails(userDetails);
        int id = userDao.addUser(user);
        user = userDao.getUserHibernate(id);
        user.getuDetails().setName("newName");
        user.getuDetails().setSurname("newSurname");
        user.getuDetails().setAge(111);
        userDetails = user.getuDetails();
        boolean result = userDetailsDao.updateUserDetails(userDetails);
        userDao.deleteUser("TestLogin");
        Assertions.assertTrue(result);
    }
}
