package com.discwords.discwords.controller;


import com.discwords.discwords.model.UserDTO;
import com.discwords.discwords.service.LoginSignupService;
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
    public ResponseEntity<String> registerCustomerHandler(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(loginSignupService.signupUser(userDTO), HttpStatus.CREATED);
    }


    //login
    @PostMapping("/api/loginUser")
    public ResponseEntity<String> loginCustomerHandler(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(loginSignupService.loginUser(userDTO), HttpStatus.ACCEPTED);
    }


}
