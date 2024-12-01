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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamwiski.wildskills.Entity.SkillOfferingEntity;
import com.teamwiski.wildskills.Service.SkillOfferingService;

@RestController
@RequestMapping("/api/wildSkills/skilloffering")
@CrossOrigin(origins = "http://localhost:5173")
public class SkillOfferingController {
    @Autowired
    SkillOfferingService skillserv;

     //Create
    @PostMapping("/postSkillOfferingRecord")
    public SkillOfferingEntity postSkillOfferingRecord(@RequestBody SkillOfferingEntity category){
        return skillserv.postSkillOfferingRecord(category);
    }
    //Read
    @GetMapping("/getAllSkillOfferingRecord")
    public List<SkillOfferingEntity>getAllSkillOfferingRecord(){
        return skillserv.getAllSkillOfferingRecord();
    }
    
    //Update
    @PutMapping("/putSkillOfferingDetails")
    public SkillOfferingEntity putSkillOfferingDetails(@RequestParam int id, @RequestBody SkillOfferingEntity newSkillOfferingDetails ){
        return skillserv.putSkillOfferingDetails(id,newSkillOfferingDetails);
    }

    //Delete
    @DeleteMapping("/deleteSkillOfferingDetails/{id}")
    public String deleteSkillOffering(@PathVariable int id){
        return skillserv.deleteSkillOffering(id);
    }

    //Create Client Side
    @PostMapping("/student/{studentId}/postSkillOfferingRecord/client")
    public SkillOfferingEntity postSkillOfferingRecordClient(@RequestBody SkillOfferingEntity category, @PathVariable int studentId){
        return skillserv.postSkillOfferingRecordClient(category,studentId);
    } 
     //Read Client Side
    @GetMapping("/student/{studentId}/getAllSkillOfferingRecord/client")
    public List<SkillOfferingEntity>getAllSkillOfferingRecordClient(@PathVariable int studentId){
        return skillserv.getAllSkillOfferingRecordClient(studentId);
    }
    @PutMapping("/student/{studentId}/putSkillOfferingDetails/client/{id}")
    public SkillOfferingEntity putSkillOfferingDetailsClient(@PathVariable int studentId,@PathVariable int id,@RequestBody SkillOfferingEntity newSkillOfferingDetails) {
        return skillserv.putSkillOfferingDetailsClient(id, newSkillOfferingDetails, studentId);
    }
   
    //Delete Client Side
    @DeleteMapping("/student/{studentId}/deleteSkillOfferingDetails/client/{id}")
    public String deleteSkillOfferingClient(@PathVariable int studentId, @PathVariable int id){
        return skillserv.deleteSkillOffering(id);
    }

    //Diri rani -- Search Offering Query
    @GetMapping("/search")
    public List<SkillOfferingEntity> searchOfferingsSQL(@RequestParam String query) {
        return skillserv.searchOfferingsSQL(query);
    }

    //Diri rani -- Search Offering Query category filter
    @GetMapping("/searchByCategory")
    public List<SkillOfferingEntity> searchOfferingsByCategory(@RequestParam String query) {    
        return skillserv.searchOfferingsByCategorySQL(query);
    }
}
