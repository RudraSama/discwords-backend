package com.discwords.discwords.websocket;

import com.discwords.discwords.model.Profile;
import com.discwords.discwords.model.User;
import com.discwords.discwords.repository.ProfileRepo;
import com.discwords.discwords.repository.UserRepo;
import com.discwords.discwords.service.JWTService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private final JWTService jwtService;
    private final ProfileRepo profileRepo;
    private final Logger LOGGER = LogManager.getLogger();

    public WebTokenFilter(JWTService jwtService, ProfileRepo profileRepo){
        this.jwtService = jwtService;
        this.profileRepo = profileRepo;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel messageChannel){
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if(StompCommand.CONNECT == accessor.getCommand()){
           String token = accessor.getFirstNativeHeader("x-access-token");


           if(jwtService.validJwtToken(token)){

               String userId = jwtService.extractUserId(token).toString();
               Optional<Profile> profileRes = profileRepo.findByUserId(Long.parseLong(userId));

               if (profileRes.isEmpty()){
                   throw new RuntimeException("Invalid User");
               }
               UserMap.addToUserList(accessor.getSessionId(), profileRes.get());

           }
           else{
               throw new RuntimeException("JWT Token not valid");
           }
        }

        return message;
    }
}
