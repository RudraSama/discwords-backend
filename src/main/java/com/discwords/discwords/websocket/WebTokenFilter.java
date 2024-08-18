package com.discwords.discwords.websocket;

import com.discwords.discwords.model.User;
import com.discwords.discwords.repository.UserRepo;
import com.discwords.discwords.service.JWTService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class WebTokenFilter implements ChannelInterceptor {

    private JWTService jwtService;
    private UserRepo userRepo;

    public WebTokenFilter(JWTService jwtService, UserRepo userRepo){
        this.jwtService = jwtService;
        this.userRepo = userRepo;
    }


    @Override
    public Message<?> preSend(Message<?> message, MessageChannel messageChannel){

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if(StompCommand.CONNECT == accessor.getCommand()){
           String token = accessor.getFirstNativeHeader("access_token");

           if(jwtService.validJwtToken(token)){

               String email = jwtService.extractEmail(token).toString();

               Optional<User> userOptional = userRepo.findByEmail(email);

               if (userOptional.isEmpty()){
                   throw new RuntimeException("Invalid User");
               }


           }
           else{
               throw new RuntimeException("JWT Token not valid");
           }
        }

        return message;
    }

}
