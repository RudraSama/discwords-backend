package com.discwords.discwords.controller;


import com.discwords.discwords.model.Profile;
import com.discwords.discwords.DTOs.TokenRequestDTO;
import com.discwords.discwords.DTOs.UserDTO;
import com.discwords.discwords.DTOs.UserSessionDTO;
import com.discwords.discwords.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService){
        this.authorizationService = authorizationService;
    }


    //register
    @PostMapping("/api/registerUser")
    public ResponseEntity<UserSessionDTO> registerUserHandler(@RequestBody UserDTO userDTO){
        try {
            return new ResponseEntity<>(authorizationService.signupUser(userDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //login
    @PostMapping("/api/loginUser")
    public ResponseEntity<UserSessionDTO> loginUserHandler(@RequestBody UserDTO userDTO){
        try {
            return new ResponseEntity<>(authorizationService.loginUser(userDTO), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //login with Google
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/loginUserWithGoogle")
    public ResponseEntity<UserSessionDTO> loginUserWithGoogleHandler(@RequestBody TokenRequestDTO tokenRequest) throws Exception{
        return new ResponseEntity<>(authorizationService.loginUserWithGoogle(tokenRequest), HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/signupUserWithGoogle")
    public ResponseEntity<UserSessionDTO> signupUserWithGoogleHandler(@RequestBody TokenRequestDTO tokenRequest) throws Exception{
        return new ResponseEntity<>(authorizationService.signupUserWithGoogle(tokenRequest), HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/checkAuthorization")
    public ResponseEntity<Profile> checkAuthorizationHandler(@RequestHeader(value = "x-access-token") String token){
        return new ResponseEntity<>(authorizationService.authorizeUser(token), HttpStatus.ACCEPTED);
    }


}
