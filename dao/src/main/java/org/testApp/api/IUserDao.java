package org.testApp.api;

import org.testApp.User;
import org.testApp.filters.UserFilter;
import java.util.List;

public interface IUserDao {
    long add(User user);
    List<User> getUsers(UserFilter userFilter);
    User getUser(String userLogin);
    long updateUserForAdmin(String oldLogin, User newUser);
    boolean delete(String login);
    int countOfUsers();
    long updateUserPassword(String newPassword, User user);
    long updateUserEmail(String newEmail, User user);

}
