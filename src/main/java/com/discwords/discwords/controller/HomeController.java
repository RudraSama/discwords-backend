package com.discwords.discwords.controller;
import com.discwords.discwords.model.User;
import com.discwords.discwords.repository.UserRepo;
import com.discwords.discwords.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    JWTService jwtService;


   @Autowired
    UserRepo userRepo;


    @GetMapping("/")
    public String greet(){
        User user = new User();
        user.setUserId(12449);
        user.setEmail("Email@email.com");
        user.setUsername("johndoe");
        userRepo.save(user);
        return "Hii";
    }


    @GetMapping("/test")
    public String greet2(){
        String token = jwtService.generateToken("hiii@gmail.com", "123455");
        return "Hii2";
    }
}
