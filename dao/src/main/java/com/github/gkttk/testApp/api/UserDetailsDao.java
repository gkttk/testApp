package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.UserDetails;

public interface UserDetailsDao {
    UserDetails getUserDetails(int userId);
    boolean updateUserDetails(UserDetails newUserDetails);


}
