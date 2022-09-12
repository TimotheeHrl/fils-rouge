package com.filRouge.filRouge.chat;

import lombok.Data;

/**
 * POJO representing the chat message
 */
@Data
public class ChatMessage {
    private String text;
    private String username;

    private  String avatar;
    public ChatMessage(){

    }

    public ChatMessage(String text ,String username, String avatar) {
        this.text = text;
        this.username = username;
        this.avatar = avatar;
    }

}
