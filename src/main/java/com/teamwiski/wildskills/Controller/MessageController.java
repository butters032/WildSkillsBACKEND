package com.teamwiski.wildskills.Controller;

import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamwiski.wildskills.Entity.MessageEntity;
import com.teamwiski.wildskills.Service.ChatService;
import com.teamwiski.wildskills.Service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(method=RequestMethod.GET,path="/api/wildSkills/message/")
public class MessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService charv;
    private final MessageService mserv;
    
    @Autowired
    public MessageController(SimpMessagingTemplate messagingTemplate, ChatService charv, MessageService mserv){
        this.messagingTemplate = messagingTemplate;
        this.charv = charv;
        this.mserv = mserv;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/chat")
    public MessageEntity sendMessage(MessageEntity message) {
        MessageEntity savedMessage = mserv.postMessageRecord(message);
        return savedMessage;
    }
 
    @MessageMapping("/sendMessage/{chatId}")
    public void sendMessageToChat(@PathVariable int chatId,MessageEntity message) {
        MessageEntity savedMessage = mserv.assignMessageToChat(chatId, message);
        messagingTemplate.convertAndSend("/topic/chat/" + chatId, savedMessage);
    }



    // Create a new message and assign it to a chat
    @PostMapping("/saveMessage")
    public MessageEntity saveMessage(@RequestBody MessageEntity message, @RequestParam int chatId) {
        return mserv.assignMessageToChat(chatId, message);
    }
    
    // Get all messages by chatId
    @GetMapping("/getMessages/{chatId}")
    public List<MessageEntity> getMessagesByChatId(@PathVariable int chatId) {
        return mserv.getMessagesByChatId(chatId);
    }

    //Create
    @PostMapping("/postMessageRecord")
    public MessageEntity postMessageRecord(@RequestBody MessageEntity mess){
        return mserv.postMessageRecord(mess);
    }

    //Read
    @GetMapping("/getAllMessage")
    public List<MessageEntity>getAllMessage(){
        return mserv.getAllMessage();
    }

    //Update
    @PutMapping("/putMessageDetails/{id}")
    public MessageEntity putMessageDetails(@PathVariable int id, @RequestBody MessageEntity newMessageDetails){
        return mserv.putMessageDetails(id, newMessageDetails);
    }

    //Delete
    @DeleteMapping("/deleteMessageDetails/{id}")
    public String deleteMessage(@PathVariable int id){
        return mserv.deleteMessage(id);
    }
}
