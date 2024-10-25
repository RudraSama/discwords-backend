package com.discwords.discwords.websocket;


import com.discwords.discwords.model.Conversation;
import com.discwords.discwords.repository.ConversationRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;


import java.util.Optional;

@Component
public class WebSocketEventListener {

    private SimpUserRegistry simpUserRegistry;
    private final Logger LOGGER = LogManager.getLogger();
    private final ConversationRepo conversationRepo;

    public WebSocketEventListener(ConversationRepo conversationRepo){
        this.conversationRepo = conversationRepo;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){

    }

    @EventListener
    public void handleWebSocketSubscriberListener(SessionSubscribeEvent event){
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());

        final String[] splitedURL = accessor.getDestination().split("/");

        if(splitedURL[2].equals("conversation")){
            String conversation_id = splitedURL[3];
            Optional<Conversation> conversationRes = conversationRepo.findById(Long.parseLong(conversation_id));

            if (conversationRes.isEmpty()){
                throw new RuntimeException();
            }

            Conversation conversation = conversationRes.get();
            long profileId = UserMap.getProfile(accessor.getSessionId()).getProfileId();

            //if profileId is not in the conversation then throw error
            if(!(conversation.getProfile_id1() == profileId || conversation.getProfile_id2() == profileId)){
                //TODO
                //have to prevent unauthorized client from subscribing.
            }
        }

    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){

    }
}
