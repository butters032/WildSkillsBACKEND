package com.teamwiski.wildskills.Entity;

import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblchat")
public class ChatEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "chat_Id")
    private int chatId;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "chat", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MessageEntity> message = new ArrayList<>();   
    
    @JsonIgnore
    @ManyToMany(mappedBy = "chats")
    private Set<StudentEntity> students = new HashSet<>();

    @OneToOne(mappedBy="chat")
    @JsonBackReference
    private SkillExchangeEntity cht;

    public ChatEntity(){
        super();
    }

    public ChatEntity(int chatId, List<MessageEntity> message){
        this.chatId = chatId;
        this.message = message;
    }

    public ChatEntity(int chatId, Set<StudentEntity> students){
        this.chatId=chatId;
        this.students=students;
    }
    public int getChatId(){
        return chatId;
    }
    public void setChatId(int chatId){
        this.chatId=chatId;
    }

    public Set<StudentEntity> getStudent() {
        return students;
    }

    public void setStudent(Set<StudentEntity> students) {
        this.students = students;
    }

    public SkillExchangeEntity getCht() {
        return cht;
    }

    public void setCht(SkillExchangeEntity cht) {
        this.cht = cht;
    } 

    public void setMsg(List<MessageEntity> message) {
        this.message=message;
    }

    public List<MessageEntity> getMessages(){
        return message;
    }
}