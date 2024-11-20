package com.teamwiski.wildskills.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.ChatEntity;
import com.teamwiski.wildskills.Entity.SkillExchangeEntity;
import com.teamwiski.wildskills.Entity.StudentEntity;
import com.teamwiski.wildskills.Repository.ChatRepository;
import com.teamwiski.wildskills.Repository.StudentRepository;

@Service
public class ChatService {
    @Autowired
    ChatRepository chapo;

    @Autowired
    StudentRepository strepo;

    public ChatService(){
        super();
    }

    //Create
    public ChatEntity postChatRecord(ChatEntity chat){
        return chapo.save(chat);
    }

    public ChatEntity postChat(ChatEntity chat, Set<Integer> studentIds) {
        Set<StudentEntity> students = new HashSet<>(strepo.findAllById(studentIds));
        chat.setStudent(students);
        // Update both sides of the relationship
        for (StudentEntity student : students) {
            student.getChat().add(chat);
        }
        chat = chapo.save(chat);
        return chat;
    }
    

    //Create Chat ith Student
    /* 
    public ChatEntity postChat(ChatEntity chat, Set<Integer> studentIds){
        Set<StudentEntity> students = new HashSet<>();
        for (int studentId : studentIds){
            StudentEntity student = strepo.findById(studentId).orElseThrow();
            students.add(student);
        }
        chat.setStudent(students);
        return chapo.save(chat);
    }
    */
    //Read
    public List<ChatEntity>getAllChat(){
        return chapo.findAll();
    }

    //Read chat with Student
    public List<ChatEntity> getAllChat(int studentId){
        List<ChatEntity> chat = new ArrayList<>();
        chapo.findByStudentsStudentId(studentId).forEach(chat::add);
        return chat;
    }

    //ReadnCHats by studentId
    public ChatEntity getChat(int studentId) {
		return chapo.findById(studentId).orElseThrow();
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

    //Update Chats with Students
    public ChatEntity putChat(int id, ChatEntity newChat, Set<Integer> studentIds){
        ChatEntity chat = chapo.findById(id).orElseThrow();
        Set<StudentEntity> students = new HashSet<>();
            for (int studentId : studentIds){
                StudentEntity student = strepo.findById(studentId).orElseThrow();
                students.add(student);
            }
        chat.setStudent(students);
        return chapo.save(chat);
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
