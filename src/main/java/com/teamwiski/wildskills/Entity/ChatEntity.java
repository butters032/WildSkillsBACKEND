package com.teamwiski.wildskills.Entity;

import java.util.*;

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

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "chat", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MessageEntity> message = new ArrayList<>();   
    
    @JsonIgnore
    @ManyToMany(mappedBy = "chats")
    private Set<StudentEntity> students = new HashSet<>();

    @JsonIgnore
    @OneToOne
	@JoinColumn(name="skill_exchangeid", referencedColumnName = "SkillExchangeID")
	private SkillExchangeEntity user;

    
    public ChatEntity(){
        super();
    }

    public ChatEntity(int chatId, List<MessageEntity> message){
        this.chatId = chatId;
        this.message = message;
    }

    public ChatEntity(int chatId, Set<StudentEntity> students, SkillExchangeEntity user){
        this.chatId=chatId;
        this.students=students;
    }

    public ChatEntity(int chatId, SkillExchangeEntity user){
        this.chatId=chatId;
        this.user=user;
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
        return user;
    }

    public void setCht(SkillExchangeEntity user) {
        this.user = user;
    } 

    public void setMsg(List<MessageEntity> message) {
        this.message=message;
    }

    public List<MessageEntity> getMessages(){
        return message;
    }
}