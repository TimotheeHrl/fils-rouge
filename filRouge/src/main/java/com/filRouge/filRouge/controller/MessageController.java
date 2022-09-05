package com.filRouge.filRouge.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 36000000)
public class MessageController {

        @MessageMapping("/chat")
        @SendTo("/topic/messages")
        public Message sendMessage(Message message) {
            return message;
        }
}
