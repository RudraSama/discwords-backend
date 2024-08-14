package com.discwords.discwords.websocket;
import com.discwords.discwords.model.Profile;
import com.discwords.discwords.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;


@Configuration
@EnableWebSocket
public class SocketHandler extends AbstractWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
    private Map<String, Profile> profiles = new HashMap<String, Profile>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{

        sessions.add(session);
        Profile profile = (Profile) session.getAttributes().get("profile");
        profiles.put(session.getId(), profile);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception{
        sessions.remove(session);
    }

}
