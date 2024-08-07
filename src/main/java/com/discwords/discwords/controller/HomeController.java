package com.discwords.discwords.controller;

import com.discwords.discwords.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    JWTService jwtService;

    @GetMapping("/")
    public String greet(){
        return "Hii";
    }


    @GetMapping("/test")
    public String greet2(){
        System.out.println(jwtService.generateToken("hiii@gmail.com", "123455"));
        return "Hii2";
    }
}
