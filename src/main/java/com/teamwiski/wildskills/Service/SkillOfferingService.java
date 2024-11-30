package com.teamwiski.wildskills.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.CategoryEntity;
import com.teamwiski.wildskills.Entity.SkillExchangeEntity;
import com.teamwiski.wildskills.Entity.StudentEntity;
import com.teamwiski.wildskills.Entity.SkillOfferingEntity;
import com.teamwiski.wildskills.Repository.CategoryRepository;
import com.teamwiski.wildskills.Repository.SkillOfferingRepository;
import com.teamwiski.wildskills.Repository.StudentRepository;

@Service
public class SkillOfferingService {
    @Autowired
    SkillOfferingRepository skillrepo;

    @Autowired 
    private CategoryRepository crepo;

    @Autowired
    private StudentRepository srepo;

    public SkillOfferingService(){
        super();
    }
    //Create
    public SkillOfferingEntity postSkillOfferingRecord(SkillOfferingEntity skillOffering) {       
        return skillrepo.save(skillOffering);
    }    
    //Read
    public List<SkillOfferingEntity>getAllSkillOfferingRecord(){
        return skillrepo.findAll();
    }

    //Update
    @SuppressWarnings("finally")
    public SkillOfferingEntity putSkillOfferingDetails(int id, SkillOfferingEntity newSkillOfferingDetails) {
         SkillOfferingEntity skillOffering = new SkillOfferingEntity(); 
         try { 
            skillOffering = skillrepo.findById(id).get(); 
            skillOffering.setIsActive(newSkillOfferingDetails.getIsActive()); 
            skillOffering.setDescription(newSkillOfferingDetails.getDescription()); 
            skillOffering.setTitle(newSkillOfferingDetails.getTitle()); 
            skillOffering.setSkills(newSkillOfferingDetails.getSkills()); 
            CategoryEntity category = newSkillOfferingDetails.getCategory(); 
            if (category != null && category.getCategoryId() != 0) { 
                category = crepo.findById(category.getCategoryId()) 
                    .orElseThrow(() -> new RuntimeException("Category not found")); 
                skillOffering.setCategory(category); 
                } 
            }catch (NoSuchElementException nex) { 
                throw new NameNotFoundException("Skill Offering " + id + " not found"); 
            } finally {
                 return skillrepo.save(skillOffering); 
                } 
            }

    //Delete
    public String deleteSkillOffering(int id) {
        String msg = "";        
        
        if (skillrepo.findById(id).isPresent()) {
            skillrepo.deleteById(id); 
            msg = "SkillOffering deleted!";
        } else {
            msg = id + " NOT FOUND";
        }
        
        return msg;
    }

    //Create Client Side
    public SkillOfferingEntity postSkillOfferingRecordClient(SkillOfferingEntity skillOffering, int studentId) {
        StudentEntity student= srepo.findById(studentId).get();
        skillOffering.setStudent(student);      
        return skillrepo.save(skillOffering);


    }    

      //Read Client Side
    public List<SkillOfferingEntity>getAllSkillOfferingRecordClient(int studentId){
        List<SkillOfferingEntity> skilloffering = new ArrayList<>();
        skillrepo.findByStudentStudentId(studentId).forEach(skilloffering::add);
        return skilloffering;
    }

    //Update Client Side
    @SuppressWarnings("finally")
    public SkillOfferingEntity putSkillOfferingDetailsClient(int id, SkillOfferingEntity newSkillOfferingDetails, int studentId) {
        SkillOfferingEntity skillOffering = null; 
        try {
            // Fetch the existing skill offering by ID
            skillOffering = skillrepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Skill Offering " + id + " not found"));
    
            // Validate if the studentId matches the existing record
            if (skillOffering.getStudent() == null || skillOffering.getStudent().getStudentId() != studentId) {
                throw new RuntimeException("Unauthorized Access! Student ID does not match.");
            }
    
            // Update the fields if validation passes
            skillOffering.setIsActive(newSkillOfferingDetails.getIsActive());
            skillOffering.setDescription(newSkillOfferingDetails.getDescription());
            skillOffering.setTitle(newSkillOfferingDetails.getTitle());
            skillOffering.setSkills(newSkillOfferingDetails.getSkills());
    
            // Handle category update
            CategoryEntity category = newSkillOfferingDetails.getCategory();
            if (category != null && category.getCategoryId() != 0) {
                category = crepo.findById(category.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));
                skillOffering.setCategory(category);
            }
        } catch (NoSuchElementException nex) {
            throw new RuntimeException("Skill Offering " + id + " not found", nex);
        }
        return skillrepo.save(skillOffering);
    }
    
    
        //Delete Client Side
	    public String deleteSkillOfferingClient(int id, int studentId) {
		SkillOfferingEntity skilloffering = skillrepo.findById(id).orElseThrow();

		String msg="";
		if (srepo.findById(id)!=null && skilloffering.getStudent().getStudentId() == studentId) {
			srepo.deleteById(id);
			msg="SKill Offering" + id + "successfully deleted!";
		} else if (skilloffering.getStudent().getStudentId() != studentId) {
			msg="Unauthorized Access!";
		} else {
			msg="SKill Offering" + id + "not found!";
		}
		return msg;
	}
   

}
