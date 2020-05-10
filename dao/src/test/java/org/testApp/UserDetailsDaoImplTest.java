package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.api.UserDao;
import org.testApp.api.UserDetailsDao;


public class UserDetailsDaoImplTest {
    private static UserDetailsDao userDetailsDao;
    private static UserDao userDao;

    @BeforeAll
    public static void createInstance() {
        userDetailsDao = UserDetailsDaoImpl.getInstance();
        userDao = UserDaoImpl.getInstance();
    }


    @Test
    public void getUserDetailsHibernateTest(){
        User user = userDao.getUserByLoginHibernate("admin");
        int userId = user.getId();
        UserDetails userDetailsFromDB = userDetailsDao.getUserDetails(userId);
        Assertions.assertNotNull(userDetailsFromDB);
        Assertions.assertAll(
                ()-> Assertions.assertEquals(userDetailsFromDB.getName(), user.getuDetails().getName()),
                ()-> Assertions.assertEquals(userDetailsFromDB.getSurname(), user.getuDetails().getSurname()),
                ()-> Assertions.assertEquals(userDetailsFromDB.getAge(), user.getuDetails().getAge()));
    }

    @Test
    public void  updateUserDetailsHibernateTest(){
        User user = new User("TestLogin", "TestPassword", "test@gmail.ru");
        UserDetails userDetails = new UserDetails(null,null,null,null,user);
        user.setuDetails(userDetails);
        Integer id = userDao.addHibernate(user);
        user = userDao.getUserHibernate(id);
        user.getuDetails().setName("newName");
        user.getuDetails().setSurname("newSurname");
        user.getuDetails().setAge(111);
        userDetails = user.getuDetails();
        Boolean result = userDetailsDao.updateUserDetails(userDetails);
        userDao.deleteUserHibernate("TestLogin");
        Assertions.assertTrue(result);

    }
}
