package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.UserDao;
import net.sf.ehcache.CacheManager;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.github.gkttk.testApp.config.DaoConfig;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testCacheUser() {
        User user1 = userDao.getUserHibernate(1);
        User user2 = userDao.getUserHibernate(1);
        int size = CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("org.testApp.User").getSize();
        Assertions.assertTrue(size > 0);
    }


    @Test
    public void getUserHibernateTest() {
        int userId = 1;
        User user = userDao.getUserHibernate(userId);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(user),
                () -> Assertions.assertEquals(1, user.getId())
        );
    }

    @Test
    public void updateUserHibernateTest() {
        User user = new User("testUser", "test", "test@mail.ru");
        int userId = userDao.addHibernate(user);
        user.setId(userId);
        sessionFactory.getCurrentSession().evict(user);
        User newUser = new User(userId, "testUser321", "test321", "test@mail.ru321");
        boolean result = userDao.updateUserHibernate(newUser);
        userDao.deleteUserHibernate("testUser321");
        Assertions.assertTrue(result);
    }

    @Test
    public void getUserByLoginHibernateTest() {
        User user = new User("testUser", "test", "test@mail.ru");
        userDao.addHibernate(user);
        User userFromDB = userDao.getUserByLoginHibernate("testUser");
        Assertions.assertNotNull(userFromDB);
        Assertions.assertEquals(user, userFromDB);
        userDao.deleteUserHibernate("testUser");
    }

    @Test
    public void addHibernateTest() {
        User user = new User("TestLogin", "TestPassword", "test@gmail.ru");
        UserDetails userDetails = new UserDetails(null, null, null, null, user);
        user.setuDetails(userDetails);
        Integer id = userDao.addHibernate(user);
        userDao.deleteUserHibernate("TestLogin");
        Assertions.assertNotNull(id);
    }

    @Test
    public void getUsersHibernateTest() {
        List<User> users = new ArrayList<>();
        users = userDao.getUsersHibernate();
        Assertions.assertNotNull(users);
    }

    @Test
    public void deleteUserHibernateTest() {
        User user = new User("testUser", "test", "test@mail.ru");
        userDao.addHibernate(user);
        boolean result = userDao.deleteUserHibernate("testUser");
        Assertions.assertTrue(result);
    }

    @Test
    public void updateUserForAdminHibernateTest() {
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addHibernate(testUser);
        String oldUserLogin = testUser.getLogin();
        User newTestUser = new User("test2", "testPass2", "test2@mail.ru");
        long result = userDao.updateUserForAdminHibernate(oldUserLogin, newTestUser);
        userDao.deleteUserHibernate("test");
        Assertions.assertNotEquals(0, result);
    }

    @Test
    public void updateUserEmailHibernateTest() {
        String newEmail = "newTestEmail@gmail.ru";
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addHibernate(testUser);
        long result = userDao.updateUserEmailHibernate(newEmail, testUser);
        userDao.deleteUserHibernate("test");
        Assertions.assertNotEquals(0, result);
    }

    @Test
    public void updateUserPasswordHibernateTest() {
        String newPassword = "newTestPassword";
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addHibernate(testUser);
        long result = userDao.updateUserEmailHibernate(newPassword, testUser);
        userDao.deleteUserHibernate("test");
        Assertions.assertNotEquals(0, result);
    }

    @Test
    public void testGetCountOfUsers() {
        int result = userDao.countOfUsers();
        Assertions.assertTrue(result >= 0);
    }

}
