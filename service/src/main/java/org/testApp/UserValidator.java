package org.testApp;

import org.testApp.api.IUserDao;
import org.testApp.api.IValidator;
import java.util.List;

public class UserValidator implements IValidator {

    private static volatile UserValidator instance;
    private IUserDao daoUser = UserDao.getInstance();

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
        User user = daoUser.getUserHibernate(userLogin);
        return user.getPassword().equals(userPassword);
    }


}
