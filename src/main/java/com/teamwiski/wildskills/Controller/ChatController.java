package com.teamwiski.wildskills.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.teamwiski.wildskills.Entity.ChatEntity;
import com.teamwiski.wildskills.Entity.MessageEntity;
import com.teamwiski.wildskills.Entity.StudentEntity;
import com.teamwiski.wildskills.Service.ChatService;
import com.teamwiski.wildskills.Service.MessageService;

@RestController
@CrossOrigin(origins = "https://localhost:5173")
@RequestMapping(method=RequestMethod.GET,path="/api/wildSkills/chat")
public class ChatController {
    // private final SimpMessagingTemplate messagingTemplate;
    //private final ChatService charv;
    //private final MessageService meser;

    @Autowired
    ChatService charv; 

    /* 
    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate, ChatService charv, MessageService meser) {
        this.messagingTemplate = messagingTemplate;
        this.charv = charv;
        this.meser = meser;
    }

    // Real-time chat messaging
    @MessageMapping("/sendMessage")
    @SendTo("/topic/chat")
    public MessageEntity sendMessage(@RequestBody MessageEntity message) {
        MessageEntity savedMessage = meser.postMessageRecord(message);
        return savedMessage;
    }

    @MessageMapping("/sendMessage/{chatId}")
    public void sendMessageToChat(@PathVariable int chatId, @RequestBody MessageEntity message) {
        MessageEntity savedMessage = meser.assignMessageToChat(chatId, message);
        messagingTemplate.convertAndSend("/topic/chat/" + chatId, savedMessage);
    }
    */

    //Create
    @PostMapping("/postChatRecord")
    public ChatEntity postChatRecord(@RequestBody ChatEntity chat){
        return charv.postChatRecord(chat);
    }

    //Create with students
    @PostMapping("/students")
    public ChatEntity postChat(@RequestBody ChatEntity chat,@RequestParam Set<Integer> studentIds, int skillExchangeId){
        return charv.postChat(chat,studentIds,skillExchangeId);
    }


    //Read
    @GetMapping("/getAllChat")
    public List<ChatEntity>getAllChat(){
        return charv.getAllChat();
    }

    //Read By Id
    @GetMapping("/getChat/{id}")
    public ChatEntity getChatById(@PathVariable int id){
        return charv.getChat(id);
    }

    //Read By Student Id
    @GetMapping("/student/{studentId}")
    public List<ChatEntity> getChatsByStudenttId(@PathVariable int studentId){
        return charv.getAllChat(studentId);
    }

    //Update
    @PutMapping("/putChatDetails")
    public ChatEntity putChatDetails(@RequestParam int id, @RequestBody ChatEntity newChatDetails ){
        return charv.putChatDetails(id,newChatDetails);
    }
    
    //Update ith student
    @PutMapping("/students/{id}")
    public ChatEntity updateChatithStudent(@PathVariable int id, @RequestBody ChatEntity chat, @RequestParam Set<Integer> studentIds){
        return charv.putChat(id,chat,studentIds);
    }

    //Delete
    @DeleteMapping("/deleteChatDetails/{id}")
    public String deleteChat(@PathVariable int id){
        return charv.deleteChat(id);
    }

}
