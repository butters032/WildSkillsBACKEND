package com.teamwiski.wildskills.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tblskilloffering")
public class SkillOfferingEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int skillOfferingId;

    private String status;
    private String description;
    public SkillOfferingEntity(){
        super();
    }
    public SkillOfferingEntity(int skillOfferingId, String status, String description){
        super();
        this.skillOfferingId=skillOfferingId;
        this.status=status;
        this.description=description;
    }

    public int getSkillOfferingId(){
        return skillOfferingId;
    }
    public void setSkillOfferingId(int skillOfferingId){
        this.skillOfferingId=skillOfferingId;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }

}
