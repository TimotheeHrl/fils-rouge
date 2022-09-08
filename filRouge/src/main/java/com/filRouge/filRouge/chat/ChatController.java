package com.filRouge.filRouge.chat;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

// The chat message-handling Controller
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChatController {
    // mapped to handle chat messages to the /sendmsg destination
    @MessageMapping("/sendmsg")
    // the return value is broadcast to all subscribers of /chat/messages
    @SendTo("/chat/messages")
    public ChatMessage chat(ChatMessage message) throws Exception {
        System.out.println("Message received: " + message.getText());
        return new ChatMessage(message.getText(), message.getUsername(), message.getAvatar());
    }
}
