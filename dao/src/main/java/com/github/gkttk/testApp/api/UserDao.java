package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.User;
import java.util.List;

public interface UserDao {
    int addUser(User user);
    List<User> getUsers();
    User getUserByLogin(String login);
    boolean deleteUser(String login);
    long updateUserForAdmin(String oldUserLogin, User newUser);
    long updateUserEmail(String newEmail, User user);
    long updateUserPassword(String newPassword, User user);
    User getUserHibernate(Integer userId);
    int countOfUsers();
    boolean updateUser(User user);

}
