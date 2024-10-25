package com.teamwiski.wildskills.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamwiski.wildskills.Entity.StudentEntity;
import com.teamwiski.wildskills.Service.StudentService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(method=RequestMethod.GET,path="/api/wildSkills/student")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {
    @Autowired
    StudentService studServ;

    //Create
    @PostMapping("/postStudentRecord")
    public StudentEntity postStudentRecord(@RequestBody StudentEntity student){
        return studServ.postStudentRecord(student);
    }

    //Read
    @GetMapping("/getStudentRecord")
    public List<StudentEntity>getStudentRecord(){
        return studServ.getAllStudentRecord();
    }

    //Update
    @PutMapping("/putStudentRecord")
    public StudentEntity putStudentRecord(@RequestParam int id, @RequestBody StudentEntity newStudentRecord){
        return studServ.putStudentRecord(id, newStudentRecord);
    }

    //Delete
    @DeleteMapping("/deleteStudentRecord/{id}")
    public String deleteStudentRecord(@PathVariable int id){
        return studServ.deleteStudentRecord(id);
    }
}