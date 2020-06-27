package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {
    @Mock
    private static UserDao userDao;

    @InjectMocks
    private static UserValidator userValidator;

    @Test
    public void testCheckLoginInDB() {
        String testLogin = "test";
        User testUser = new User("test", "testPass", "test@mail.ru");
        when(userDao.getUsersHibernate()).thenReturn(new ArrayList<>(Collections.singletonList(testUser)));
        boolean result = userValidator.checkLoginInDB(testLogin);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCheckUserPassword() {
        String testLogin = "test";
        User testUser = new User("test", "testPass", "test@mail.ru");
        when(userDao.getUserByLoginHibernate(testLogin)).thenReturn(testUser);
        boolean result = userValidator.checkUserPassword(testLogin, "testPass");
        Assertions.assertTrue(result);
    }


}
