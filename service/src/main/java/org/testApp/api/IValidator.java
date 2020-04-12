package org.testApp.api;

public interface IValidator {
   boolean checkLoginInDB(String login);
   boolean checkUserPassword(String userLogin, String userPassword);
}
