package com.filRouge.filRouge.chat;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


    @Component
    public class WebSocketEventListener {

        private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

        @Autowired
        private SimpMessageSendingOperations messagingTemplate;

        @EventListener
        public void handleWebSocketConnectListener(SessionConnectedEvent event) {
            logger.info("Received a new web socket connection");
        }

        @EventListener
        public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
            StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

            String username = (String) headerAccessor.getSessionAttributes().get("username");
            if(username != null) {
                logger.info("User Disconnected : " + username);

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setUsername(username);
                chatMessage.setAvatar("https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50");


                messagingTemplate.convertAndSend("/app/public", chatMessage);
            }
        }

}
