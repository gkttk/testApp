package org.testApp.api;

import org.testApp.UserDetails;

public interface UserDetailsDao {

    UserDetails getUserDetails(Integer userId);
    Boolean updateUserDetails(UserDetails newUserDetails);


}
