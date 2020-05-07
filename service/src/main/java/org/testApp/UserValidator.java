package org.testApp;

import org.testApp.api.UserDao;
import org.testApp.api.Validator;
import java.util.List;

public class UserValidator implements Validator {

    private static volatile UserValidator instance;
    private UserDao daoUser = UserDaoImpl.getInstance();

    private UserValidator(){}

    public static synchronized UserValidator getInstance(){
        if(instance == null){
            instance = new UserValidator();
        }
        return  instance;
    }
    @Override
    public boolean checkLoginInDB(String login){
        List<User> users = daoUser.getUsersHibernate(null);
        return users.stream().anyMatch(user->user.getLogin().equalsIgnoreCase(login));
    }

    @Override
    public boolean checkUserPassword(String userLogin, String userPassword) {
        User user = daoUser.getUserByLoginHibernate(userLogin);
        return user.getPassword().equals(userPassword);
    }


}
