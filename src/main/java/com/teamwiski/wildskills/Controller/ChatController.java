	package com.teamwiski.wildskills.Controller;

import java.util.List;

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

import com.teamwiski.wildskills.Entity.ChatEntity;
import com.teamwiski.wildskills.Service.ChatService;

@RestController
@RequestMapping(method=RequestMethod.GET,path="/api/chat")
@CrossOrigin(origins = "http://localhost:5173/")
public class ChatController {
    @Autowired
    ChatService charv;

    //Create
    @PostMapping("/postChatRecord")
    public ChatEntity postChatRecord(@RequestBody ChatEntity chat){
        return charv.postChatRecord(chat);
    }
    //Read
    @GetMapping("/getAllChat")
    public List<ChatEntity>getAllChat(){
        return charv.getAllChat();
    }
    //Update
    @PutMapping("/putChatDetails")
    public ChatEntity putChatDetails(@RequestParam int id, @RequestBody ChatEntity newChatDetails ){
        return charv.putChatDetails(id,newChatDetails);
    }
    //UPDATE
    //Delete
    @DeleteMapping("/deleteChatDetails/{id}")
    public String deleteChat(@PathVariable int id){
        return charv.deleteChat(id);
    }

}
