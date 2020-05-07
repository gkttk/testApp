package org.testApp.api;

import org.testApp.UserDetails;

public interface UserDetailsDao {
    Integer addUserDetails(UserDetails userDetails);
    UserDetails getUserDetails(Integer userId);
    Boolean updateUserDetails(UserDetails newUserDetails);
    Boolean deleteUserDetails(Integer userId);

}
