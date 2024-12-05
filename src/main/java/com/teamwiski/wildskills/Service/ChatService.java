package com.teamwiski.wildskills.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.ChatEntity;
import com.teamwiski.wildskills.Entity.SkillExchangeEntity;
import com.teamwiski.wildskills.Entity.StudentEntity;
import com.teamwiski.wildskills.Repository.ChatRepository;
import com.teamwiski.wildskills.Repository.SkillExchangeRepository;
import com.teamwiski.wildskills.Repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class ChatService {
    @Autowired
    ChatRepository chapo;

    @Autowired
    StudentRepository strepo;

    @Autowired
    StudentService stserv;

    @Autowired
    SkillExchangeRepository srepo;

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

    // Create Chat with Students
    @Transactional
    public ChatEntity postChat(ChatEntity chat, Set<Integer> studentIds, int skillExchangeId) {
        SkillExchangeEntity exchange = srepo.findById(skillExchangeId).get();
        List<StudentEntity> studentsList = strepo.findAllById(studentIds);
        Set<StudentEntity> students = new HashSet<>(studentsList);

        chat.setStudent(students);
        chat.setCht(exchange);
        
        
        ChatEntity savedChat = chapo.save(chat);
        
        for (StudentEntity student : students) {
            student.getChats().add(savedChat);
            strepo.save(student);
        }
        return savedChat;
    }



  /*  
    // Create Chat with Students
    public ChatEntity postChat(ChatEntity chat, Set<Integer> studentIds) {
        Set<StudentEntity> students = new HashSet<>();
        for (Integer id : studentIds) {
            Optional<StudentEntity> student = strepo.findById(id);
            student.ifPresent(students::add);
        }
        chat.setStudent(students);
        return chapo.save(chat);
    }
 */


    // Get Chat by ID
    public ChatEntity getChat(int chatId) {
        return chapo.findById(chatId).orElseThrow(() -> 
            new RuntimeException("Chat with ID " + chatId + " not found"));
    }

    // Get Chats by Student ID
    public List<ChatEntity> getAllChat(int studentId) {
        return chapo.findByStudentsStudentId(studentId);
    }

    public ChatEntity putChat(int chatId, ChatEntity newChatDetails, Set<Integer> studentIds) {
        ChatEntity existingChat = getChat(chatId);

        Set<StudentEntity> students = new HashSet<>();
        for (Integer id : studentIds) {
            Optional<StudentEntity> student = strepo.findById(id);
            student.ifPresent(students::add);
        }

        existingChat.setMsg(newChatDetails.getMessages());
        existingChat.setStudent(students);
        return chapo.save(existingChat);
    }

/*
    //Read all chats by studentId
    public Set<ChatEntity> getAllChat(int studentId) {
        StudentEntity student = strepo.findById(studentId).get();
        return student.getChats();
    }

    //Read chats by studentId
    public ChatEntity getChat(int studentId) {
        return chapo.findById(studentId).get();
    }

    //Create Chat by StudentId
    @SuppressWarnings("finally")
    public ChatEntity postChat(ChatEntity chat, int studentId){
        ChatEntity newChat = new ChatEntity();
        try{
            //verify student id
			StudentEntity student = strepo.findById(studentId).get();

            //verify exchangeEntity

            //set exchange 
            
            //set initiator
            newChat = chapo.save(chat);
            
            int chatId = newChat.getChatId();

            //assign to associative entity exchange_student
			//kaduha kay duha ka students ang involved
            stserv.assignSkillExchange(studentId, chatId);
        }catch (NoSuchElementException nex) {
			throw new NameNotFoundException("Chat with ID " + studentId + " not found");
		} finally {
			return newChat;
		}
    }

    //Update SkillExchange
    @SuppressWarnings("finally")
    public ChatEntity putChat(int id, ChatEntity newChat, int studentId){
        ChatEntity chat = new ChatEntity();

        try{
            chat = chapo.findById(id).get();

            if(chat.getStudent().getStudentId() != studentId){
                throw new SecurityException("Unauthorized Access!");
            }
        }
    }
*/
}
