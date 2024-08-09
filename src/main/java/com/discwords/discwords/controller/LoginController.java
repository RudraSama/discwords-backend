package com.discwords.discwords.controller;


import com.discwords.discwords.model.TokenRequestDTO;
import com.discwords.discwords.model.UserDTO;
import com.discwords.discwords.model.UserSession;
import com.discwords.discwords.model.UserSessionDTO;
import com.discwords.discwords.service.LoginSignupService;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginController {

    @Autowired
    LoginSignupService loginSignupService;


    //register
    @PostMapping("/api/registerUser")
    public ResponseEntity<UserSessionDTO> registerUserHandler(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(loginSignupService.signupUser(userDTO), HttpStatus.CREATED);
    }


    //login
    @PostMapping("/api/loginUser")
    public ResponseEntity<UserSessionDTO> loginUserHandler(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(loginSignupService.loginUser(userDTO), HttpStatus.ACCEPTED);
    }


    //login with google
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/loginUserWithGoogle")
    public ResponseEntity<UserSessionDTO> loginUserWithGoogleHandler(@RequestBody TokenRequestDTO tokenRequest) throws Exception{
        return new ResponseEntity<>(loginSignupService.loginUserWithGoogle(tokenRequest), HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/signupUserWithGoogle")
    public ResponseEntity<UserSessionDTO> signupUserWithGoogleHandler(@RequestBody TokenRequestDTO tokenRequest) throws Exception{
        return new ResponseEntity<>(loginSignupService.signupUserWithGoogle(tokenRequest), HttpStatus.ACCEPTED);
    }


}
