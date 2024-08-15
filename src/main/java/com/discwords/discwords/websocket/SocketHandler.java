package com.discwords.discwords.websocket;
import com.discwords.discwords.model.DirectMessageDTO;
import com.discwords.discwords.model.Profile;
import com.discwords.discwords.repository.DirectMessageRepo;
import com.discwords.discwords.repository.ProfileRepo;
import com.discwords.discwords.service.DirectMessageService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
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


    private DirectMessageService directMessageService;


    public SocketHandler() {}
    public SocketHandler(DirectMessageService directMessageService){
        this.directMessageService = directMessageService;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{

        sessions.add(session);
        Profile profile = (Profile) session.getAttributes().get("profile");
        profiles.put(session.getId(), profile);
        System.out.println(profiles);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception{
        sessions.remove(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception{

            String msg = message.getPayload().toString();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(msg);
            long profile_id = jsonNode.get("profile_id").asLong();
            String user_message = jsonNode.get("message").asText();
            long conversation_id = jsonNode.get("conversation_id").asLong();
            long receiver_id = jsonNode.get("receiver_id").asLong();

            directMessageService.saveMessage(conversation_id, profile_id, user_message);

            for(String key: profiles.keySet()) {
                if(receiver_id == profiles.get(key).getProfile_id()){
                    for(WebSocketSession session1: sessions){
                        if(session1.getId().equals(key)){
                            session1.sendMessage(new TextMessage(user_message));
                        }
                    }
                }
            }




    }


}
