package com.filRouge.filRouge.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WSController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send(String message) throws Exception {
        String time = new java.util.Date().toString();
        String messageToSend = message + " " + time;
        return messageToSend;
    }
}