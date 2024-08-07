package com.discwords.discwords.service;


import com.discwords.discwords.model.User;
import com.discwords.discwords.model.UserDTO;
import com.discwords.discwords.model.UserSecret;
import com.discwords.discwords.repository.UserRepo;
import com.discwords.discwords.repository.UserSecretRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginSignupServiceImpl implements LoginSignupService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserSecretRepo userSecretRepo;

    @Autowired
    JWTService jwtService;


    //method to login user
    @Override
    public String loginUser(UserDTO user){

        Optional<User> userRes = userRepo.findByEmail(user.getEmail());
        if(userRes.isEmpty()){
            System.out.println("User not found");
            return "";
        }

        User existingUser = userRes.get();
        Optional<UserSecret> userSecretRes = userSecretRepo.findById(existingUser.getUserId());

        if(userSecretRes.isEmpty()){
            System.out.println("User secret is empty");
            return "";
        }

        UserSecret userSecret = userSecretRes.get();

        if(BCrypt.checkpw(userSecret.getPassword(), user.getPassword())){
            String jwtToken = jwtService.generateToken(existingUser.getEmail(), Long.toString(existingUser.getUserId()));
            return jwtToken;
        }

        return "";

    }

    @Override
    public String signupUser(UserDTO user){

       Optional<User> userRes = userRepo.findByEmail(user.getEmail());

       if(!userRes.isEmpty()){
           System.out.println("User already exist");
           return "";
       }

       String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
       Long userId = generateId();

       User newUser = new User(userId, user.getEmail(), user.getUsername());
       userRepo.save(newUser);

       UserSecret newUserSecret = new UserSecret(userId, password);
       userSecretRepo.save(newUserSecret);


       //token generation

        String token = jwtService.generateToken(newUser.getEmail(), Long.toString(newUser.getUserId()));

        return token;
    }


    private Long generateId(){
        Long time = System.currentTimeMillis();
        time = time%1000000000;
        time = time*1000;
        time = time+(long)(Math.random()*1000);

        return time;
    }

}

