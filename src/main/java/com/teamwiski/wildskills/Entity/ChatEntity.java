package com.teamwiski.wildskills.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tblchat")
public class ChatEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int chatId;

    private String message;
    private String timeStamp;

    public ChatEntity(){
        super();
    }

    public ChatEntity(int chatId, String message, String timeStamp){
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

    public String getTimeStamp(){
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp){
        this.timeStamp=timeStamp;
    }
}