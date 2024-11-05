package com.teamwiski.wildskills.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.CategoryEntity;
import com.teamwiski.wildskills.Entity.SkillOfferingEntity;
import com.teamwiski.wildskills.Repository.CategoryRepository;
import com.teamwiski.wildskills.Repository.SkillOfferingRepository;

@Service
public class SkillOfferingService {
    @Autowired
    SkillOfferingRepository skillrepo;

    @Autowired 
    private CategoryRepository crepo;

    public SkillOfferingService(){
        super();
    }
    //Create
    public SkillOfferingEntity postSkillOfferingRecord(SkillOfferingEntity skillOffering) {
        // Check if the category information is provided
        if (skillOffering.getCategory() != null && skillOffering.getCategory().getCategoryId() != 0) {
            int categoryId = skillOffering.getCategory().getCategoryId();
    
            // Attempt to fetch the existing category from the database
            Optional<CategoryEntity> existingCategoryOpt = crepo.findById(categoryId);
    
            if (existingCategoryOpt.isPresent()) {
                // Set the fetched category to skill offering if found
                skillOffering.setCategory(existingCategoryOpt.get());
            } else {
                throw new RuntimeException("Category with ID " + categoryId + " not found in the database.");
            }
        } else {
            // Provide more information on the invalid input
            throw new IllegalArgumentException("The provided category ID is invalid: " +
                    (skillOffering.getCategory() == null ? "null category" : "categoryId=" + skillOffering.getCategory().getCategoryId()));
        }
    
        // Save the skill offering with the associated category
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

}
