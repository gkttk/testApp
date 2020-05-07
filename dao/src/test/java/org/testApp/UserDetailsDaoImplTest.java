package org.testApp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.ConnectUtils.AutoIncrementCompressor;
import org.testApp.api.UserDao;
import org.testApp.api.UserDetailsDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDetailsDaoImplTest {
    private static UserDetailsDao userDetailsDao;
    private static UserDao userDao;

    @BeforeAll
    public static void createInstance() {
        userDetailsDao = UserDetailsDaoImpl.getInstance();
        userDao = UserDaoImpl.getInstance();
    }

    @Test
    public void addUserDetailsHibernateTest(){
        User user = userDao.getUserHibernate("admin");
        UserDetails userDetails = new UserDetails(null, "testName", "testSurname", 100, null, user);
        user.setuDetails(userDetails);
        int userId = user.getId();
        Integer detailsId = userDetailsDao.addUserDetails(userDetails);
        userDetailsDao.deleteUserDetails(userId);
        Assertions.assertNotNull(detailsId);
    } //hibernate

    @Test
    public void getUserDetailsHibernateTest(){
        User user = userDao.getUserHibernate("admin");
        UserDetails userDetails = new UserDetails(null, "testName", "testSurname", 100, null, user);
        user.setuDetails(userDetails);
        userDetailsDao.addUserDetails(userDetails);
        int userId = user.getId();
        UserDetails userDetailsFromDB = userDetailsDao.getUserDetails(userId);
        userDetailsDao.deleteUserDetails(userId);
        Assertions.assertNotNull(userDetailsFromDB);
        Assertions.assertAll(
                ()-> Assertions.assertEquals(userDetailsFromDB.getName(), userDetails.getName()),
                ()-> Assertions.assertEquals(userDetailsFromDB.getSurname(), userDetails.getSurname()),
                ()-> Assertions.assertEquals(userDetailsFromDB.getAge(), userDetails.getAge()));

    }

    @Test
    public void  updateUserDetailsHibernateTest(){
        User user = userDao.getUserHibernate("admin");
        UserDetails userDetails = new UserDetails(null, "testName", "testSurname", 100, null, user);
        user.setuDetails(userDetails);
        userDetailsDao.addUserDetails(userDetails);
        int userId = user.getId();
        UserDetails newUserDetails = userDetailsDao.getUserDetails(userId);
        newUserDetails.setName("NewName");
        newUserDetails.setSurname("NewSurname");
        newUserDetails.setAge(800);
        Boolean result = userDetailsDao.updateUserDetails(newUserDetails);
        userDetailsDao.deleteUserDetails(userId);
        Assertions.assertTrue(result);
    }



    @Test
    public void  deleteUserDetailsHibernateTest(){
        User user = userDao.getUserHibernate("admin");
        UserDetails userDetails = new UserDetails(null, "testName", "testSurname", 100, null, user);
        user.setuDetails(userDetails);
        userDetailsDao.addUserDetails(userDetails);
        int userId = user.getId();
        boolean result = userDetailsDao.deleteUserDetails(userId);
        Assertions.assertTrue(result);
    }





}
