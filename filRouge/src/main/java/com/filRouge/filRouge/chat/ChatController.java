package com.filRouge.filRouge.chat;
import com.filRouge.filRouge.model.Channel;
import com.filRouge.filRouge.model.Message;
import com.filRouge.filRouge.model.User;
import com.filRouge.filRouge.repository.ChannelRepository;
import com.filRouge.filRouge.repository.MessageRepository;
import com.filRouge.filRouge.repository.UserRepository;
import com.filRouge.filRouge.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// The chat message-handling Controller
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

public class ChatController {
    UserRepository userRepository;
    MessageRepository messageRepository;
   ChannelRepository channelRepository;
    ChatController(MessageRepository messageRepository, UserRepository userRepository, ChannelRepository channelRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.channelRepository = channelRepository;
    }
    // mapped to handle chat messages to the /sendmsg destination
    @MessageMapping("/sendmsg")
    // the return va
    // lue is broadcast to all subscribers of /chat/messages
    @SendTo("/chat/messages")
    public Message chat(@Payload String message,  @Header("token") String token) throws Exception {
     List<User> user =   userRepository.findUserByTokenEquals(token);
        Message message1 = new Message();
        message1.setText(message);
        message1.setUser(user.get(0));
        LocalDateTime localDateTime = LocalDateTime.now();
        message1.setLocalDateTime(localDateTime);

        Channel channel = new Channel();

        Optional<Channel> channel1 = channelRepository.findById(1L);
        if(channel1.isPresent()){
            message1.setChannel(channel1.get());
        } else {
            channel.setDescription("channel 1");
            channel.setName("general");

            channelRepository.save(channel);

            message1.setChannel(channel);

        }
        messageRepository.save(message1);
        return  message1;
    }


}
