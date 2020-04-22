package org.testApp;

import org.testApp.api.IUserDao;
import org.testApp.api.IUserService;
import java.util.List;

public class UserService implements IUserService{

    private static volatile IUserService instance;
    private IUserDao userDao = UserDao.getInstance();

    private UserService() {}

    public static synchronized IUserService getInstance(){
        if(instance == null){
            instance = new UserService();
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
