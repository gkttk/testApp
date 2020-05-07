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
import org.testApp.api.UserDao;
import org.testApp.api.UserService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private static UserDao userDao;
    @InjectMocks
    private static UserService userService;

    @BeforeAll
    public static void createInstance() {
        userService = UserServiceImpl.getInstance();
    }

    @Test
    public void testChangePassword() {
        User testUser =  new User("mockTest", "mockTestPass", "mockTest@mail.ru");
        when(userDao.updateUserPasswordHibernate(any(), any())).thenReturn(0L);
        Long result = userService.changePassword("Kirill", testUser);
        Assertions.assertEquals(0L, result);
    }

    @Test
    public void testChangeEmail(){
        User testUser =  new User("mockTest", "mockTestPass", "mockTest@mail.ru");
        when(userDao.updateUserEmailHibernate("mockTest@gmail.com",testUser)).thenReturn(0L);
        Long result = userService.changeEmail("mockTest@gmail.com", testUser);
        Assertions.assertEquals(0L, result);
  }

    @Test
    public void addUser(){
    User testUser =  new User(1000,"mockTest", "mockTestPass", "mockTest@mail.ru");
        when(userDao.addHibernate(testUser)).thenReturn(1000);
        Integer result = userService.addUser(testUser);
        Assertions.assertEquals(1000, result);
}

    @Test
    public void testGetUsersList(){
        when(userDao.getUsersHibernate(null)).thenReturn(null);
        List<User> users = userService.getUsersList();
        Assertions.assertNull(users);
    }

    @Test
    public void testGetUser(){
        User testUser = new User("testUser", "testPass", "test@mail.ru");
        when(userDao.getUserByLoginHibernate("testUser")).
                thenReturn(new User("testUser", "testPass", "test@mail.ru"));
        User userFromDb = userService.getUserByLogin("testUser");
        Assertions.assertEquals(testUser, userFromDb);
    }

    @Test
    public void testDeleteUser(){
        when(userDao.deleteUserHibernate("testUser")).thenReturn(true);
        boolean result = userService.deleteUser("testUser");
        Assertions.assertTrue(result);
    }



}
