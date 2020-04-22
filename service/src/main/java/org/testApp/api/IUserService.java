package org.testApp.api;

import org.testApp.User;
import java.util.List;

public interface IUserService {
    Integer addUser(User user);
    List<User> getUsersList();
    User getUser(String userLogin);
    boolean deleteUser(String userLogin);
    long changeEmail(String newEmail, User user);
    long changePassword(String newPassword, User user);

}
