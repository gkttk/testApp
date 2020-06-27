package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.User;
import java.util.List;

public interface UserDao {
    Integer addHibernate(User user);
    List<User> getUsersHibernate();
    User getUserByLoginHibernate(String login);
    boolean deleteUserHibernate(String login);
    long updateUserForAdminHibernate(String oldUserLogin, User newUser);
    long updateUserEmailHibernate(String newEmail, User user);
    long updateUserPasswordHibernate(String newPassword, User user);
    User getUserHibernate(Integer userId);
    int countOfUsers();
    boolean updateUserHibernate(User user);

}
