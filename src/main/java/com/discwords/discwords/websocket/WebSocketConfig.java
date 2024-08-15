package com.discwords.discwords.websocket;
import com.discwords.discwords.repository.ProfileRepo;
import com.discwords.discwords.service.DirectMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private DirectMessageService directMessageService;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(new SocketHandler(directMessageService), "/user").addInterceptors(new HttpHandshakeInceptor(profileRepo));
    }
}
