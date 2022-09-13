package com.filRouge.filRouge.controller;

import com.filRouge.filRouge.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.filRouge.filRouge.repository.ChannelRepository;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 36000000)
@RestController
@RequestMapping("/api/chat")
public class ChatRestController {

    private final ChannelRepository channelRepository;

    public ChatRestController(ChannelRepository channelRepository){
        this.channelRepository = channelRepository;
    }
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/general")
    public List<Message> getMessages(){
        System.out.println("coucou");
        return channelRepository.findById(1L).get().getMessages();
    }
}
