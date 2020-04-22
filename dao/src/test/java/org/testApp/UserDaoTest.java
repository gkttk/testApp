package org.testApp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.ConnectUtils.AutoIncrementCompressor;
import org.testApp.api.IUserDao;
import org.testApp.enums.Role;
import org.testApp.filters.UserFilter;

import java.util.ArrayList;
import java.util.List;

public class UserDaoTest {
    private static IUserDao userDao;

    @BeforeAll
    public static void createInstance() {
        userDao = UserDao.getInstance();
    }

    @Test
    public void getUserHibernateTest(){
        User user = new User("testUser", "test", "test@mail.ru");
        userDao.addHibernate(user);
        User userFromDB = userDao.getUserHibernate("testUser");
        Assertions.assertNotNull(userFromDB);
        Assertions.assertEquals(user, userFromDB);
        userDao.deleteUserHibernate("testUser");
    } //hibernate

    @Test
    public void addHibernateTest() {
        User user = new User("TestLogin", "TestPassword", "test@gmail.ru");
        Integer id = userDao.addHibernate(user);
        userDao.deleteUserHibernate("TestLogin");
        Assertions.assertNotNull(id);
    }  //hibernate

    @Test
    public void getUsersHibernateTest() {
        List<User> users = new ArrayList<>();
        users = userDao.getUsersHibernate(null);
        Assertions.assertNotNull(users);
    }  //hibernate

    @Test
    public void deleteUserHibernateTest(){
        User user = new User("testUser", "test", "test@mail.ru");
        userDao.addHibernate(user);
        boolean result = userDao.deleteUserHibernate("testUser");
        Assertions.assertTrue(result);
    } //hibernate

    @Test
    public void updateUserForAdminHibernateTest(){
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addHibernate(testUser);
        String oldUserLogin = testUser.getLogin();
        User newTestUser = new User("test2", "testPass2", "test2@mail.ru");
        long result = userDao.updateUserForAdminHibernate(oldUserLogin, newTestUser);
        userDao.deleteUserHibernate("test");
        Assertions.assertNotEquals(0, result);

    } //hibernate

    @Test
    public void updateUserEmailHibernateTest(){
        String newEmail = "newTestEmail@gmail.ru";
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addHibernate(testUser);
        long result = userDao.updateUserEmailHibernate(newEmail, testUser);
        userDao.deleteUserHibernate("test");
        Assertions.assertNotEquals(0, result);
    } //hibernate

    @Test
    public void updateUserPasswordHibernateTest(){
        String newPassword = "newTestPassword";
        User testUser = new User("test", "testPass", "test@mail.ru");
        userDao.addHibernate(testUser);
        long result = userDao.updateUserEmailHibernate(newPassword, testUser);
        userDao.deleteUserHibernate("test");
        Assertions.assertNotEquals(0, result);
    } //hibernate

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



    @Test
    public void testGetCountOfUsers() {
        int result = userDao.countOfUsers();
        Assertions.assertNotEquals(0, result);
    }




    @AfterAll
    public static void trimToSize() {
        int rows = userDao.countOfUsers();
        AutoIncrementCompressor.compressionTable("user", rows);
    }

}
