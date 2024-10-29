package com.teamwiski.wildskills.Controller;

import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamwiski.wildskills.Entity.MessageEntity;
import com.teamwiski.wildskills.Service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(method=RequestMethod.GET,path="/api/wildSkills/message/")
public class MessageController {
    @Autowired
    MessageService mserv;

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
    @PutMapping("/putMessageDetails")
    public MessageEntity putMessageDetails(@RequestParam int id, @RequestBody MessageEntity newMessageDetails){
        return mserv.putMessageDetails(id, newMessageDetails);
    }

    //Delete
    @DeleteMapping("/deleteMessageDetails/{id}")
    public String deleteMessage(@PathVariable int id){
        return mserv.deleteMessage(id);
    }
}
