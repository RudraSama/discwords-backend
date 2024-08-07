package com.discwords.discwords.controller;
import com.discwords.discwords.model.UserDetails;
import com.discwords.discwords.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    JWTService jwtService;

    @Autowired
    UserDetails userDetails;

    @GetMapping("/")
    public String greet(){
        return "Hii";
    }


    @GetMapping("/test")
    public String greet2(){
        String token = jwtService.generateToken("hiii@gmail.com", "123455");
        System.out.println(token);
        System.out.println(jwtService.extractEmail(token).toString());
        System.out.println(jwtService.isTokenValid(token, userDetails));
        return "Hii2";
    }
}
