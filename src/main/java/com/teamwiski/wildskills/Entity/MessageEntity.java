package com.teamwiski.wildskills.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="tblMessage")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int messageId;

    @Column
    private String message;
    private LocalDateTime timeStamp;

    @ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "chat_Id")
	@JsonIgnore
    private ChatEntity chat;

    public MessageEntity(){
        super();
    }

    public MessageEntity(int messageId, String message, LocalDateTime timeStamp){
        super();
        this.messageId=messageId;
        this.message=message;
        this.timeStamp=timeStamp;
    }
    public int getMessageId(){
        return messageId;
    }
    public void setMessageId(int messageId){
        this.messageId=messageId;
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

    public ChatEntity getChat() {
		return chat;
	}

	public void setChat(ChatEntity chat) {
		this.chat = chat;
	}
}
