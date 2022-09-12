package com.filRouge.filRouge.chat;
import com.filRouge.filRouge.model.User;
import com.filRouge.filRouge.repository.UserRepository;
import com.filRouge.filRouge.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

// The chat message-handling Controller
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChatController {
    @Autowired
    UserRepository userRepository;
    // mapped to handle chat messages to the /sendmsg destination
    @MessageMapping("/sendmsg")
    // the return va
    // lue is broadcast to all subscribers of /chat/messages
    @SendTo("/chat/messages")
    public ChatMessage chat(@Payload ChatMessage message,  @Header("token") String token) throws Exception {
     List<User> user =   userRepository.findUserByTokenEquals(token);
        message.setUsername(user.get(0).getUsername());
        System.out.println(message.toString());


        return  new ChatMessage(message.getText(), message.getUsername(), message.getAvatar());
    }
}
