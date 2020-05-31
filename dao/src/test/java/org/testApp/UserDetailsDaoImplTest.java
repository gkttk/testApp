package org.testApp;

import net.sf.ehcache.CacheManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.UserDao;
import org.testApp.api.UserDetailsDao;
import org.testApp.config.DaoConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class UserDetailsDaoImplTest {

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Autowired
    private UserDao userDao;


    @Test
    public void testCacheUserDetails() {
        UserDetails userDetails1 = userDetailsDao.getUserDetails(1);
        UserDetails userDetails2 = userDetailsDao.getUserDetails(1);
        int size = CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("org.testApp.UserDetails").getSize();
        Assertions.assertTrue(size > 0);
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
