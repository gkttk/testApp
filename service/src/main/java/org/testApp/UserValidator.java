package org.testApp;

import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.UserDao;
import org.testApp.api.Validator;
import java.util.List;

public class UserValidator implements Validator {


    private UserDao daoUser;

    public UserValidator(UserDao daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    public boolean checkLoginInDB(String login){
        List<User> users = daoUser.getUsersHibernate();
        boolean result = users.stream().anyMatch(user->user.getLogin().equalsIgnoreCase(login));
        return result;
    }

    @Override
    public boolean checkUserPassword(String userLogin, String userPassword) {
        User user = daoUser.getUserByLoginHibernate(userLogin);
        return user.getPassword().equals(userPassword);
    }


}
