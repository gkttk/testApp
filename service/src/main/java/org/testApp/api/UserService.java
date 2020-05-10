package org.testApp.api;

import org.testApp.User;
import java.util.List;

public interface UserService {
    Integer addUser(User user);
    List<User> getUsersList();
    User getUser(Integer userId);
    User getUserByLogin(String userLogin);
    boolean deleteUser(String userLogin);
    long changeEmail(String newEmail, User user);
    long changePassword(String newPassword, User user);
    boolean updateUser(User newUser);
}
