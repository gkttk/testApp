package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.UserDetails;

public interface UserDetailsDao {
    UserDetails getUserDetails(Integer userId);
    Boolean updateUserDetails(UserDetails newUserDetails);


}
