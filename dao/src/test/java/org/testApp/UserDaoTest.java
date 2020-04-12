package org.testApp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.ConnectUtils.AutoIncrementCompressor;
import org.testApp.api.IUserDao;
import org.testApp.enums.Role;
import org.testApp.filters.UserFilter;

import java.util.List;

public class UserDaoTest {
    private static IUserDao userDao;

    @BeforeAll
    public static void createInstance(){
        userDao = UserDao.getInstance();
    }


    @Test
    public void testAddUser(){
        User testUser = new User("testLogin1", "testPassword", "test@test.com");
        long userId = -1;
        long result = userDao.add(testUser);
        if(result != -1){
            userDao.delete("testLogin1");
        }
        Assertions.assertNotEquals(userId, result);
    }

    @Test()
    public void testDeleteUser(){
        User testUser = new User("testLogin", "testPassword", "test@test.com");
        userDao.add(testUser);
        String login = testUser.getLogin();
        boolean result = userDao.delete(login);
        Assertions.assertTrue(result);
    }

    @Test
    public void testGetUsers(){
        UserFilter userFilter = null;
        List<User> testListUser = userDao.getUsers(userFilter);
        Assertions.assertNotEquals(null, testListUser);
    }

    @Test
    public void testGetUser(){
        User testUser = new User( "testUser", "testUserPass", "test@mail.ru");
       int id =  (int)userDao.add(testUser);
       testUser.setId(id);
        String userName = testUser.getLogin();
        User userFromDb = userDao.getUser(userName);
        if(userFromDb != null){
            userDao.delete(userName);
        }
        Assertions.assertEquals(testUser, userFromDb);
    }

    @Test
    public void testGetCountOfUsers(){
        int result = userDao.countOfUsers();
        Assertions.assertNotEquals(0,result);
    }

    @Test
    public void testUpdateUserForAdmin(){
        User testUser = new User("testLogin", "testPassword", "test@test.com");
        userDao.add(testUser);
        String oldUserLogin = testUser.getLogin();
        User newTestUser = new User("newTestLogin", "newTestPassword", "newTest@test.com");
        long result = userDao.updateUserForAdmin(oldUserLogin, newTestUser);

        userDao.delete(oldUserLogin);

        Assertions.assertNotEquals(-1,result);
    }

    @Test
    public void testUpdateUserPassword(){
        User testUser = new User("testLogin1", "testPassword", "test@test.com");
        userDao.add(testUser);
        String newPassword = "newTestPass";
        userDao.updateUserPassword(newPassword, testUser);
        String userPassFromDb = userDao.getUser("testLogin1").getPassword();
        userDao.delete("testLogin1");
        Assertions.assertEquals(newPassword, userPassFromDb);
    }

    @Test
    public void Email(){
        User testUser = new User("testLogin1", "testPassword", "test@test.com");
        userDao.add(testUser);
        String newEmail = "newTestEmail@email.com";
        userDao.updateUserEmail(newEmail, testUser);
        String userEmailFromDb = userDao.getUser("testLogin1").getEmail();
        userDao.delete("testLogin1");
        Assertions.assertEquals(newEmail, userEmailFromDb);
    }


    @AfterAll
    public static void trimToSize(){
        int rows = userDao.countOfUsers();
        AutoIncrementCompressor.compressionTable("user", rows);
    }

}
