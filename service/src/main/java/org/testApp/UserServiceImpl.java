package org.testApp;

import org.testApp.api.UserDao;
import org.testApp.api.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static volatile UserService instance;
    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {}

    public static synchronized UserService getInstance(){
        if(instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public long changePassword(String newPassword, User user){
       return userDao.updateUserPasswordHibernate(newPassword,user);
    }

    @Override
    public long changeEmail(String newEmail, User user){
        return userDao.updateUserEmailHibernate(newEmail,user);
    }

    @Override
    public Integer addUser(User user) {
          return userDao.addHibernate(user);
    }

    @Override
    public List<User> getUsersList(){
        return userDao.getUsersHibernate(null);
    }

    @Override
    public User getUser(String userLogin){
        User user = userDao.getUserHibernate(userLogin);
        return user;
    }


    @Override
    public boolean deleteUser(String userLogin){
           return userDao.deleteUserHibernate(userLogin);
    }


}
