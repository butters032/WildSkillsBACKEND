package com.teamwiski.wildskills.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    //Update
    @SuppressWarnings("finally")
    public StudentEntity putStudentRecord(int studId, StudentEntity newStudent){
        StudentEntity student = new StudentEntity();
        try{
            student=studRepo.findById(studId).get();
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
