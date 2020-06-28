package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.UserDao;
import com.github.gkttk.testApp.api.Validator;
import java.util.List;

public class UserValidator implements Validator {

    private UserDao daoUser;

    public UserValidator(UserDao daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    public boolean checkLoginInDB(String login) {
        List<User> users = daoUser.getUsers();
        return users.stream().anyMatch(user -> user.getLogin().equalsIgnoreCase(login));
    }

    @Override
    public boolean checkUserPassword(String userLogin, String userPassword) {
        User user = daoUser.getUserByLogin(userLogin);
        return user.getPassword().equals(userPassword);
    }


}
