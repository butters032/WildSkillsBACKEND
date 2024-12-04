package com.teamwiski.wildskills.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.teamwiski.wildskills.Entity.AuthenticationEntity;
import com.teamwiski.wildskills.Entity.SkillExchangeEntity;
import com.teamwiski.wildskills.Entity.StudentEntity;
import com.teamwiski.wildskills.Repository.AuthenticationRepository;
import com.teamwiski.wildskills.Repository.SkillExchangeRepository;
import com.teamwiski.wildskills.Repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    StudentRepository studRepo;

    @Autowired
    AuthenticationRepository authRepo;

    @Autowired
    SkillExchangeRepository srepo;

    public StudentService(){
        super();
    }
    
    //Create
    public StudentEntity postStudentRecord(StudentEntity student){
        AuthenticationEntity authEntity = new AuthenticationEntity(); 
        authEntity.setAuthStatus(false);
        authEntity.setSessionDurationOn(LocalDateTime.now()); 
        authEntity.setSessionDurationEnd(LocalDateTime.now().plusHours(1));

        student.setAuthKey(authEntity);
        authRepo.save(authEntity);
        
        return studRepo.save(student);
    }

    //Read
    public List<StudentEntity>getAllStudentRecord(){
        return studRepo.findAll();
    }

    public StudentEntity getUserStudentRecord(int studId){
        return studRepo.findById(studId).get();
    }

    public boolean checkEmailExists(String email){
        Optional<StudentEntity> student = studRepo.findByEmail(email);
        return student.isPresent();
    }

    public Map<String, Object> login(String email, String password) {
    Optional<StudentEntity> studentOpt = studRepo.findByEmail(email);
    if (studentOpt.isPresent()) {
        StudentEntity student = studentOpt.get();
        if (student.getPassword().equals(password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "Login Successful");
            response.put("studentId", student.getStudentId());
            response.put("authId", student.getAuthKey());
            return response;
        } else {
            throw new IllegalArgumentException("Invalid Password");
        }
    } else {
        throw new NoSuchElementException("Email not found");
    }
}



    //Update
    @SuppressWarnings("finally")
    public StudentEntity putStudentRecord(int studId, StudentEntity newStudent){
        StudentEntity student = new StudentEntity();
        try{
            student=studRepo.findById(studId).get();
            student.setName(newStudent.getName());
            student.setBirthdate(newStudent.getBirthdate());
            student.setEmail(newStudent.getEmail());
            student.setPassword(newStudent.getPassword());
            student.setGender(newStudent.getGender());
        }catch(NoSuchElementException n){
            throw new NameNotFoundException("Student " + studId+ " not found.");
        }finally{
            return studRepo.save(student);
        }

    }
    


    //Delete
    public String deleteStudentRecord(int studId){
        String msg = "";
        if(studRepo.findById(studId).isPresent()){
            studRepo.deleteById(studId);
            msg = "Student Record Succesfully Deleted";
        }
        else{
            msg = "Student Record Not Found";
        }
        return msg;
    }

    //Assign Student to Skill Exchange
    public StudentEntity assignSkillExchange(int studentId, int exchangeId) {
        Set<SkillExchangeEntity> skillExchanges = null;
        StudentEntity student = studRepo.findById(studentId).get();
        SkillExchangeEntity skillExchange = srepo.findById(exchangeId).get();
        skillExchanges = student.getSkillExchanges();
        skillExchanges.add(skillExchange);
        student.setSkillExchanges(skillExchanges);
        return studRepo.save(student);
    }
    
    public int searchTotalUsers() {
    	return studRepo.searchTotalUsers();
    }
}
