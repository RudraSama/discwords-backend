package com.discwords.discwords.service;

import com.discwords.discwords.model.User;
import com.discwords.discwords.model.UserDTO;

public interface LoginSignupService {

    public String loginUser(UserDTO user);
    public String signupUser(UserDTO user);

}
