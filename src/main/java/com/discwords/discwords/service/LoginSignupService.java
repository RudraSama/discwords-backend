package com.discwords.discwords.service;

import com.discwords.discwords.model.TokenRequestDTO;
import com.discwords.discwords.model.UserDTO;
import com.discwords.discwords.model.UserSession;

public interface LoginSignupService {

    public UserSession loginUser(UserDTO user);

    public UserSession loginUserWithGoogle(TokenRequestDTO tokenRequestDTO) throws Exception;

    public UserSession signupUser(UserDTO user);

    public UserSession signupUserWithGoogle(TokenRequestDTO tokenRequestDTO) throws Exception;
}