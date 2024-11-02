package com.teamwiski.wildskills.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.StudentEntity;
import com.teamwiski.wildskills.Repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    StudentRepository studRepo;

    public StudentService(){
        super();
    }
    
    //Create
    public StudentEntity postStudentRecord(StudentEntity student){
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
}
