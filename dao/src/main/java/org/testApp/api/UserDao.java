package org.testApp.api;

import org.testApp.User;
import java.util.List;

public interface UserDao {
    Integer addHibernate(User user); //hibernate
    List<User> getUsersHibernate(); //hibernate
    User getUserByLoginHibernate(String login); //hibernate
    boolean deleteUserHibernate(String login); //hibernate
    long updateUserForAdminHibernate(String oldUserLogin, User newUser); //hibernate
    long updateUserEmailHibernate(String newEmail, User user); //hibernate
    long updateUserPasswordHibernate(String newPassword, User user); //hibernate
    User getUserHibernate(Integer userId);
    int countOfUsers();
    boolean updateUserHibernate(User user);

}
