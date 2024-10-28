package com.teamwiski.wildskills.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="tblchat")
public class ChatEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int chatId;

    private String message;
    private LocalDateTime timeStamp;

    public ChatEntity(){
        super();
    }

    public ChatEntity(int chatId, String message, LocalDateTime timeStamp){
        super();
        this.chatId=chatId;
        this.message=message;
        this.timeStamp=timeStamp;
    }
    public int getChatId(){
        return chatId;
    }
    public void setChatId(int chatId){
        this.chatId=chatId;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }

    public LocalDateTime getTimeStamp() {
    	return timeStamp;
    }
    
    @PrePersist
    public void setTimeStamp(){
        this.timeStamp=LocalDateTime.now();
    }
}