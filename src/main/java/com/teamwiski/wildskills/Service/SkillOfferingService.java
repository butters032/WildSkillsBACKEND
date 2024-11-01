package com.teamwiski.wildskills.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.SkillOfferingEntity;
import com.teamwiski.wildskills.Repository.SkillOfferingRepository;

@Service
public class SkillOfferingService {
    @Autowired
    SkillOfferingRepository skillrepo;

    public SkillOfferingService(){
        super();
    }
    //Create
    public SkillOfferingEntity postSkillOfferingRecord(SkillOfferingEntity skilloffering){
        return skillrepo.save(skilloffering);
    }

    //Read
    public List<SkillOfferingEntity>getAllSkillOfferingRecord(){
        return skillrepo.findAll();
    }

    //Update
    @SuppressWarnings("finally")
    public SkillOfferingEntity putSkillOfferingDetails(int id,SkillOfferingEntity newSkillOfferingDetails){
        SkillOfferingEntity skilloffering=new SkillOfferingEntity();
        try {           
            skilloffering=skillrepo.findById(id).get();            
            
            skilloffering.setIsActive(newSkillOfferingDetails.getIsActive());
            skilloffering.setDescription(newSkillOfferingDetails.getDescription());
        } catch (NoSuchElementException nex) {
            throw new NameNotFoundException("Skill Offering" + id + "not found");
        }finally{
            return skillrepo.save(skilloffering);
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
