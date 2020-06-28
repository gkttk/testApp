package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private static UserDao userDao;
    @InjectMocks
    private static UserServiceImpl userService;

    @Test
    public void testChangePassword() {
        User testUser = new User("mockTest", "mockTestPass", "mockTest@mail.ru");
        when(userDao.updateUserPassword(any(), any())).thenReturn(0L);
        Long result = userService.changePassword("Kirill", testUser);
        Assertions.assertEquals(0L, result);
    }

    @Test
    public void testChangeEmail() {
        User testUser = new User("mockTest", "mockTestPass", "mockTest@mail.ru");
        when(userDao.updateUserEmail("mockTest@gmail.com", testUser)).thenReturn(0L);
        Long result = userService.changeEmail("mockTest@gmail.com", testUser);
        Assertions.assertEquals(0L, result);
    }

    @Test
    public void addUser() {
        User testUser = new User(1000, "mockTest", "mockTestPass", "mockTest@mail.ru");
        when(userDao.addUser(testUser)).thenReturn(1000);
        Integer result = userService.addUser(testUser);
        Assertions.assertEquals(1000, result);
    }

    @Test
    public void testGetUsersList() {
        when(userDao.getUsers()).thenReturn(null);
        List<User> users = userService.getUsersList();
        Assertions.assertNull(users);
    }

    @Test
    public void testGetUser() {
        User testUser = new User("testUser", "testPass", "test@mail.ru");
        when(userDao.getUserByLogin("testUser")).
                thenReturn(new User("testUser", "testPass", "test@mail.ru"));
        User userFromDb = userService.getUserByLogin("testUser");
        Assertions.assertEquals(testUser, userFromDb);
    }

    @Test
    public void testDeleteUser() {
        when(userDao.deleteUser("testUser")).thenReturn(true);
        boolean result = userService.deleteUser("testUser");
        Assertions.assertTrue(result);
    }

    @Test
    public void testGetUserByLogin() {
        String mockLogin = "testLogin";
        User mockUser = new User("testLogin", "testPassword", "testEmail");
        when(userDao.getUserByLogin(mockLogin)).thenReturn(new User("testLogin", "testPassword", "testEmail"));
        User result = userService.getUserByLogin(mockLogin);
        Assertions.assertEquals(result, mockUser);
    }

    @Test
    public void testUpdateUser() {
        User mockUser = new User();
        when(userDao.updateUser(mockUser)).thenReturn(false);
        boolean result = userService.updateUser(mockUser);
        Assertions.assertFalse(result);

    }
}
