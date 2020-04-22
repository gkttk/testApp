package org.testApp.api;

import org.testApp.User;
import org.testApp.filters.UserFilter;
import java.util.List;

public interface IUserDao {
    Integer addHibernate(User user); //hibernate
    List<User> getUsersHibernate(UserFilter userFilter); //hibernate
    User getUserHibernate(String login); //hibernate
    boolean deleteUserHibernate(String login); //hibernate
    long updateUserForAdminHibernate(String oldUserLogin, User newUser); //hibernate
    long updateUserEmailHibernate(String newEmail, User user); //hibernate
    long updateUserPasswordHibernate(String newPassword, User user); //hibernate

    int countOfUsers();

    /*long updateUserPassword(String newPassword, User user);*/ //JDBC
    /*long updateUserEmail(String newEmail, User user);*/ //JDBC
    /*Integer add(User user);*/  //JDBC
    /*List<User> getUsers(UserFilter userFilter);*/ //JDBC
    /*User getUser(String userLogin);*/  //JDBC
    /*boolean delete(String login);*/ //JDBC
    /*long updateUserForAdmin(String oldLogin, User newUser);*/ //JDBC
}
