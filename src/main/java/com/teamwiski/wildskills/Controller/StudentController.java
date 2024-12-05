package com.teamwiski.wildskills.Controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teamwiski.wildskills.Entity.StudentEntity;
import com.teamwiski.wildskills.Repository.StudentRepository;
import com.teamwiski.wildskills.Service.StudentService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Base64;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/api/wildSkills/student")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {
    @Autowired
    StudentService studServ;

    //Create
    @PostMapping("/postStudentRecord")
    public StudentEntity postStudentRecord(@RequestBody StudentEntity student){
        /* 
        if (student.getAvatar() != null) {
            byte[] decodedAvatar = Base64.getDecoder().decode(student.getAvatar());
            student.setAvatar(decodedAvatar);
        }*/

        return studServ.postStudentRecord(student);
    }

    /* 
    @PostMapping("/postStudentRecord")
    public ResponseEntity<StudentEntity> postStudentRecord(@RequestBody StudentEntity student) {
        return ResponseEntity.ok(studServ.postStudentRecord(student));
    }
    
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @PostMapping("/postStudentRecord")
    public ResponseEntity<String> registerStudent(@RequestBody StudentEntity student) {
        try {
            // Decode Base64 avatar if it's present
            

            // Save the student entity
            studentRepository.save(student);

            return ResponseEntity.ok("Student registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error registering student: " + e.getMessage());
        }
    }
    */


    //Read
    @GetMapping("/getStudentRecord")
    public List<StudentEntity>getStudentRecord(){
        return studServ.getAllStudentRecord();
    }

    @GetMapping("/getUserStudentRecord")
    public StudentEntity getUserStudentRecord(@RequestParam int id){
        return studServ.getUserStudentRecord(id);
    }


    @GetMapping("/getCheckEmailExist")
    public boolean checkEmailExists(@RequestParam String email){
        return studServ.checkEmailExists(email);
    }


    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:5173")
    public Map<String, Object> login(@RequestBody StudentEntity student) {
        return studServ.login(student.getEmail(), student.getPassword());
    }


    

    //Update
    @PutMapping("/putStudentRecord")
    public StudentEntity putStudentRecord(@RequestParam int id, @RequestBody StudentEntity newStudentRecord){
        return studServ.putStudentRecord(id, newStudentRecord);
    }

    //Delete
    @DeleteMapping("/deleteStudentRecord/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public String deleteStudentRecord(@PathVariable int id){
        return studServ.deleteStudentRecord(id);
    }

    //Assign Student to Skill Exchange
    /*@PutMapping("/{studentId}/skillExchange/{exchangeId}")
    public StudentEntity assignSkillExchange(@PathVariable int studentId, @PathVariable int exchangeId) {
        return studServ.assignSkillExchange(studentId, exchangeId);
    }*/
    
    
    @GetMapping("/countTotalUsers")
    public int searchTotalUsers() {
    	return studServ.searchTotalUsers();
    }
}