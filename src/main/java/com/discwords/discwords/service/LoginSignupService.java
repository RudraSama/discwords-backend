package com.discwords.discwords.service;

import com.discwords.discwords.model.TokenRequestDTO;
import com.discwords.discwords.model.UserDTO;
import com.discwords.discwords.model.UserSessionDTO;

public interface LoginSignupService {

    public UserSessionDTO loginUser(UserDTO user);

    public UserSessionDTO loginUserWithGoogle(TokenRequestDTO tokenRequestDTO) throws Exception;

    public UserSessionDTO signupUser(UserDTO user);

    public UserSessionDTO signupUserWithGoogle(TokenRequestDTO tokenRequestDTO) throws Exception;
}