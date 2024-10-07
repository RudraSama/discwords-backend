package com.discwords.discwords.service;

import com.discwords.discwords.DTOs.TokenRequestDTO;
import com.discwords.discwords.DTOs.UserDTO;
import com.discwords.discwords.DTOs.UserSessionDTO;
import com.discwords.discwords.model.Profile;

public interface AuthorizationService {

    public UserSessionDTO loginUser(UserDTO user) throws Exception;
    public UserSessionDTO loginUserWithGoogle(TokenRequestDTO tokenRequestDTO) throws Exception;
    public UserSessionDTO signupUser(UserDTO user) throws Exception;
    public UserSessionDTO signupUserWithGoogle(TokenRequestDTO tokenRequestDTO) throws Exception;
    public Profile authorizeUser(String jwtToken);
}