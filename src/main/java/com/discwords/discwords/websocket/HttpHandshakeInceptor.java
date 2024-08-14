package com.discwords.discwords.websocket;

import com.discwords.discwords.model.Profile;
import com.discwords.discwords.repository.ProfileRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;


public class HttpHandshakeInceptor implements HandshakeInterceptor {


    private ProfileRepo profileRepo;

    public HttpHandshakeInceptor(ProfileRepo profileRepo){
        this.profileRepo = profileRepo;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Map attribtues) throws Exception{

        if(request instanceof ServerHttpRequest){
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;

            HttpHeaders httpHeaders = serverHttpRequest.getHeaders();
            long profile_id = Long.valueOf(httpHeaders.get("profile_id").get(0));

            Optional<Profile> profile = profileRepo.findById(profile_id);

            if (profile.isEmpty()){
                return false;
            }

            attribtues.put("profile", profile.get());

        }

        return true;
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Exception exception){

    }
}
