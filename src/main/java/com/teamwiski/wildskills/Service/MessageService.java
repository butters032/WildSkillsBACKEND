package com.teamwiski.wildskills.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.MessageEntity;
import com.teamwiski.wildskills.Repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository merpo;

    public MessageService(){
        super();
    }

    //Create
    public MessageEntity postMessageRecord(MessageEntity mess){
        return merpo.save(mess);
    }

    //Read
    public List<MessageEntity>getAllMessage(){
        return merpo.findAll();
    }

    //Update
    @SuppressWarnings("finally")
    public MessageEntity putMessageDetails(int id,MessageEntity newMessageDetails){
        MessageEntity mess=new MessageEntity();

        try{
            mess=merpo.findById(id).get();
            
            mess.setMessage(newMessageDetails.getMessage());
        }catch (NoSuchElementException nex){
            throw new NameNotFoundException("Message "+id+"not found");
        }finally{
            return merpo.save(mess);
        }
    }

    //Delete
    public String deleteMessage(int id){
        String msg = "";

        if (merpo.findById(id).isPresent()) {
            merpo.deleteById(id);
            msg = "Message Deleted";
        }else{
            msg = "No message to be deleted";
        }
        
        return msg;
    }
}
