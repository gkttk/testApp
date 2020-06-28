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
        int size = CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("com.github.gkttk.testApp.User").getSize();
        Assertions.assertTrue(size > 0);
    }

    @Test
    public void testGetUser() {
        int userId = 1;
        User user = userDao.getUserHibernate(userId);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(user),
                () -> Assertions.assertEquals(1, user.getId())
        );
    }

    @Test
    public void testAddUser() {
        User user = new User("TestLogin", "TestPassword", "test@gmail.ru");
        UserDetails userDetails = new UserDetails(null, null, null, null, user);
        user.setuDetails(userDetails);
        int id = userDao.addUser(user);
        userDao.deleteUser("TestLogin");
        Assertions.assertTrue(id >= 0);
    }

    @Test
    public void testUpdateUser() {
        User user = new User("testUser", "test", "test@mail.ru");
        int userId = userDao.addUser(user);
        user.setId(userId);
        sessionFactory.getCurrentSession().evict(user);
        User newUser = new User(userId, "testUser321", "test321", "test@mail.ru321");
        boolean result = userDao.updateUser(newUser);
        userDao.deleteUser("testUser321");
        Assertions.assertTrue(result);
    }

    @Test
    public void testGetUserByLogin() {
        User user = new User("testUser", "test", "test@mail.ru");
        userDao.addUser(user);
        User userFromDB = userDao.getUserByLogin("testUser");
        Assertions.assertNotNull(userFromDB);
        Assertions.assertEquals(user, userFromDB);
        userDao.deleteUser("testUser");
    }


    @Test
    public void getUsersHibernateTest() {
        List<User> users = userDao.getUsers();
        Assertions.assertNotNull(users);
    }

    @Test
    public void deleteUserHibernateTest() {
        User user = new User("testUser", "test", "test@mail.ru");
        userDao.addUser(user);
        boolean result = userDao.deleteUser("testUser");
        Assertions.assertTrue(result);
    }

    @Test
    public void testUpdateUserForAdmin() {
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addUser(testUser);
        String oldUserLogin = testUser.getLogin();
        User newTestUser = new User("test2", "testPass2", "test2@mail.ru");
        long result = userDao.updateUserForAdmin(oldUserLogin, newTestUser);
        userDao.deleteUser("test");
        Assertions.assertNotEquals(-1L, result);
    }

    @Test
    public void testUpdateUserEmail() {
        String newEmail = "newTestEmail@gmail.ru";
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addUser(testUser);
        long result = userDao.updateUserEmail(newEmail, testUser);
        userDao.deleteUser("test");
        Assertions.assertNotEquals(-1L, result);
    }

    @Test
    public void testUpdateUserPassword() {
        String newPassword = "newTestPassword";
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addUser(testUser);
        long result = userDao.updateUserEmail(newPassword, testUser);
        userDao.deleteUser("test");
        Assertions.assertNotEquals(-1L, result);
    }

    @Test
    public void testCountOfUsers() {
        int result = userDao.countOfUsers();
        Assertions.assertTrue(result >= 0);
    }

}
