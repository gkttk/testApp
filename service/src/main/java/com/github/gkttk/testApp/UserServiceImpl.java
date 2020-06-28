package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.UserDao;
import com.github.gkttk.testApp.api.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long changePassword(String newPassword, User user) {
        return userDao.updateUserPassword(newPassword, user);
    }

    @Override
    public long changeEmail(String newEmail, User user) {
        return userDao.updateUserEmail(newEmail, user);
    }

    @Override
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<User> getUsersList() {
        return userDao.getUsers();
    }

    @Override
    public User getUser(Integer userId) {
        return userDao.getUserHibernate(userId);
    }

    @Override
    public boolean deleteUser(String userLogin) {
        return userDao.deleteUser(userLogin);
    }

    @Override
    public User getUserByLogin(String userLogin) {
        return userDao.getUserByLogin(userLogin);
    }

    @Override
    public boolean updateUser(User newUser) {
        return userDao.updateUser(newUser);
    }

}
