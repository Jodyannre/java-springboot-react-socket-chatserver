package com.chatserver.controller;


import com.chatserver.model.Message;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(Message message) {
        return message;
    }

    @MessageMapping("/private-message")
    public Message privateMessage(Message message) {
        //Creating dynamic topis
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private", message);
        return message;
    }
}
