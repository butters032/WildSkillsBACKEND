package com.teamwiski.wildskills.Entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tblchat")
public class ChatEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int chatId;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "chat", cascade = CascadeType.ALL)
    private List<MessageEntity> messages;
    @JsonIgnore

    public ChatEntity(){
        super();
    }

    public ChatEntity(int chatId){
        super();
        this.chatId=chatId;
    }
    public int getChatId(){
        return chatId;
    }
    public void setChatId(int chatId){
        this.chatId=chatId;
    }
}