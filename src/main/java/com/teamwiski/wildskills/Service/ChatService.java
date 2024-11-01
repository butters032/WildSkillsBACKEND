package com.teamwiski.wildskills.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.ChatEntity;
import com.teamwiski.wildskills.Repository.ChatRepository;

@Service
public class ChatService {
    @Autowired
    ChatRepository chapo;

    public ChatService(){
        super();
    }

    //Create
    public ChatEntity postChatRecord(ChatEntity chat){
        return chapo.save(chat);
    }

    //Read
    public List<ChatEntity>getAllChat(){
        return chapo.findAll();
    }

    //Update
    @SuppressWarnings("finally")
    public ChatEntity putChatDetails(int id,ChatEntity newChatDetails){
        ChatEntity chat=new ChatEntity();
        try {           
            chat=chapo.findById(id).get();
        } catch (NoSuchElementException nex) {
            throw new NameNotFoundException("Chat" + id + "not found");
        }finally{
            return chapo.save(chat);
        }
    }

    //Delete
    public String deleteChat(int id) {
        String msg = "";        
        
        if (chapo.findById(id).isPresent()) {
            chapo.deleteById(id); 
            msg = "Message deleted";
        } else {
            msg = id + " NOT FOUND";
        }
        
        return msg;
    }

}
