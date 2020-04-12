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
       return userDao.updateUserPassword(newPassword,user);
    }

    @Override
    public long changeEmail(String newEmail, User user){
        return userDao.updateUserEmail(newEmail,user);
    }

    @Override
    public long addUser(User user) {
          return userDao.add(user);
    }

    @Override
    public List<User> getUsersList(){
        return userDao.getUsers(null);
    }

    @Override
    public User getUser(String userLogin){
        User user = userDao.getUser(userLogin);
        return user;
    }


    @Override
    public boolean deleteUser(String userLogin){
           return userDao.delete(userLogin);
    }


}
