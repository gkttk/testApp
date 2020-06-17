package org.testApp;

import net.sf.ehcache.CacheManager;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.UserDao;
import org.testApp.config.DaoConfig;

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
    } //hibernate

    @Test
    public void addHibernateTest() {
        User user = new User("TestLogin", "TestPassword", "test@gmail.ru");
        UserDetails userDetails = new UserDetails(null, null, null, null, user);
        user.setuDetails(userDetails);
        Integer id = userDao.addHibernate(user);
        userDao.deleteUserHibernate("TestLogin");
        Assertions.assertNotNull(id);
    }  //hibernate

    @Test
    public void getUsersHibernateTest() {
        List<User> users = new ArrayList<>();
        users = userDao.getUsersHibernate();
        Assertions.assertNotNull(users);
    }  //hibernate

    @Test
    public void deleteUserHibernateTest() {
        User user = new User("testUser", "test", "test@mail.ru");
        userDao.addHibernate(user);
        boolean result = userDao.deleteUserHibernate("testUser");
        Assertions.assertTrue(result);
    } //hibernate

    @Test
    public void updateUserForAdminHibernateTest() {
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addHibernate(testUser);
        String oldUserLogin = testUser.getLogin();
        User newTestUser = new User("test2", "testPass2", "test2@mail.ru");
        long result = userDao.updateUserForAdminHibernate(oldUserLogin, newTestUser);
        userDao.deleteUserHibernate("test");
        Assertions.assertNotEquals(0, result);

    } //hibernate

    @Test
    public void updateUserEmailHibernateTest() {
        String newEmail = "newTestEmail@gmail.ru";
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addHibernate(testUser);
        long result = userDao.updateUserEmailHibernate(newEmail, testUser);
        userDao.deleteUserHibernate("test");
        Assertions.assertNotEquals(0, result);
    } //hibernate

    @Test
    public void updateUserPasswordHibernateTest() {
        String newPassword = "newTestPassword";
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addHibernate(testUser);
        long result = userDao.updateUserEmailHibernate(newPassword, testUser);
        userDao.deleteUserHibernate("test");
        Assertions.assertNotEquals(0, result);
    } //hibernate

    @Test
    public void testGetCountOfUsers() {
        int result = userDao.countOfUsers();
        Assertions.assertTrue(result >= 0);
    }

    /*@AfterAll
    public static void trimToSize() {
        int rows = userDao.countOfUsers();
        AutoIncrementCompressor.compressionTable("user", rows);
    }*/


    /*@Test
    public void testAddUser() {
        User testUser = new User("testLogin1", "testPassword", "test@test.com");
        long userId = -1;
        long result = userDao.add(testUser);
        if (result != -1) {
            userDao.delete("testLogin1");
        }
        Assertions.assertNotEquals(userId, result);
    }*/  //JDBC AddUserTest

    /*@Test
    public void testDeleteUser() {
        User testUser = new User("testLogin", "testPassword", "test@test.com");
        userDao.addHibernate(testUser);
        String login = testUser.getLogin();
        boolean result = userDao.delete(login);
        Assertions.assertTrue(result);
    }*/  //JDBC DeleteUserTest

    /*@Test
    public void testGetUsers() {
        UserFilter userFilter = null;
        List<User> testListUser = userDao.getUsers(userFilter);
        Assertions.assertNotEquals(null, testListUser);
    }*/  //JDBC GetUsersTest

    /*@Test
    public void testGetUser() {
        User testUser = new User("testUser", "testUserPass", "test@mail.ru");
        int id = (int) userDao.add(testUser);
        testUser.setId(id);
        String userName = testUser.getLogin();
        User userFromDb = userDao.getUser(userName);
        if (userFromDb != null) {
            userDao.delete(userName);
        }
        Assertions.assertEquals(testUser, userFromDb);
    }*/  //JDBC GetUserTest

    /*@Test
        public void testUpdateUserForAdmin() {
            User testUser = new User("testLogin", "testPassword", "test@test.com");
            userDao.addHibernate(testUser);
            String oldUserLogin = testUser.getLogin();
            User newTestUser = new User("newTestLogin", "newTestPassword", "newTest@test.com");
            long result = userDao.updateUserForAdmin(oldUserLogin, newTestUser);

            userDao.deleteUserHibernate(oldUserLogin);

            Assertions.assertNotEquals(-1, result);
        }*/ // JDBC UpdateUserForAdminTest

    /*@Test
    public void testUpdateEmail() {
        User testUser = new User("testLogin1", "testPassword", "test@test.com");
        userDao.addHibernate(testUser);
        String newEmail = "newTestEmail@email.com";
        userDao.updateUserEmail(newEmail, testUser);
        String userEmailFromDb = userDao.getUserHibernate("testLogin1").getEmail();
        userDao.deleteUserHibernate("testLogin1");
        Assertions.assertEquals(newEmail, userEmailFromDb);
    }*/  //JDBC testUpdateEmail

    /* @Test
    public void testUpdateUserPassword() {
        User testUser = new User("testLogin1", "testPassword", "test@test.com");
        userDao.addHibernate(testUser);
        String newPassword = "newTestPass";
        userDao.updateUserPassword(newPassword, testUser);
        String userPassFromDb = userDao.getUserHibernate("testLogin1").getPassword();
        userDao.deleteUserHibernate("testLogin1");
        Assertions.assertEquals(newPassword, userPassFromDb);
    }*/ //JDBC testUpdateUserPassword


}
