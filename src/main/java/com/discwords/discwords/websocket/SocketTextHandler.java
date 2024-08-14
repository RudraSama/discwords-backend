package com.discwords.discwords.websocket;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.json.JSONObject;

import java.io.IOException;

public class SocketTextHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException{
        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        session.sendMessage(new TextMessage("hi this is from" + jsonObject.get("user")));

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        System.out.println("connection established");
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        System.out.println("connection closed");
    }
}
