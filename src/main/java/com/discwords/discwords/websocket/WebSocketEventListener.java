package com.discwords.discwords.websocket;


import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpAttributesContextHolder;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebSocketEventListener {

    private List<String> list = new ArrayList<String>();
    private SimpUserRegistry simpUserRegistry;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){
        list.add(SimpAttributesContextHolder.currentAttributes().getSessionId());

        System.out.println(list);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        System.out.println("User disconneted");
        list.remove(SimpAttributesContextHolder.currentAttributes().getSessionId());
        System.out.println(list);
    }
}
