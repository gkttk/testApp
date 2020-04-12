package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testApp.api.IUserDao;
import org.testApp.api.IUserService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private static IUserDao userDao;
    @InjectMocks
    private static IUserService userService;

    @BeforeAll
    public static void createInstance() {
        userService = UserService.getInstance();
    }

    @Test
    public void testChangePassword() {
        User testUser =  new User("mockTest", "mockTestPass", "mockTest@mail.ru");
        when(userDao.updateUserPassword(any(), any())).thenReturn(0L);
        Long result = userService.changePassword("Kirill", testUser);
        Assertions.assertEquals(0L, result);
    }

    @Test
    public void testChangeEmail(){
        User testUser =  new User("mockTest", "mockTestPass", "mockTest@mail.ru");
        when(userDao.updateUserEmail("mockTest@gmail.com",testUser)).thenReturn(0L);
        Long result = userService.changeEmail("mockTest@gmail.com", testUser);
        Assertions.assertEquals(0L, result);
  }

    @Test
    public void addUser(){
    User testUser =  new User(1000,"mockTest", "mockTestPass", "mockTest@mail.ru");
        when(userDao.add(testUser)).thenReturn(1000L);
        Long result = userService.addUser(testUser);
        Assertions.assertEquals(1000L, result);
}

    @Test
    public void testGetUsersList(){
        when(userDao.getUsers(null)).thenReturn(null);
        List<User> users = userService.getUsersList();
        Assertions.assertNull(users);
    }

    @Test
    public void testGetUser(){
        User testUser = new User("testUser", "testPass", "test@mail.ru");
        when(userDao.getUser("testUser")).
                thenReturn(new User("testUser", "testPass", "test@mail.ru"));
        User userFromDb = userService.getUser("testUser");
        Assertions.assertEquals(testUser, userFromDb);
    }

    @Test
    public void testDeleteUser(){
        when(userDao.delete("testUser")).thenReturn(true);
        boolean result = userService.deleteUser("testUser");
        Assertions.assertTrue(result);
    }



}
