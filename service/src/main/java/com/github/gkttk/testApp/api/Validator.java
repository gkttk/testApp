package com.github.gkttk.testApp.api;

public interface Validator {

   boolean checkLoginInDB(String login);
   boolean checkUserPassword(String userLogin, String userPassword);
}
