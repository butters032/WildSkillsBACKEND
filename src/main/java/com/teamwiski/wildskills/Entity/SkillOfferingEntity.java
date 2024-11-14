package com.teamwiski.wildskills.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblskilloffering")
public class SkillOfferingEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int skillOfferingId;

    private String title;
    private boolean isActive;
    private String description;
    private String skills;  

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="categoryID")
    @JsonBackReference
    
    private CategoryEntity category; 

    public SkillOfferingEntity() {
        super();
    }

    public SkillOfferingEntity(int skillOfferingId, String title, boolean isActive, String description, String skills) {
        super();
        this.skillOfferingId = skillOfferingId;
        this.title = title;
        this.isActive = isActive;
        this.description = description;
        this.skills = skills;        
    }

    public int getSkillOfferingId() {
        return skillOfferingId;
    }

    public void setSkillOfferingId(int skillOfferingId) {
        this.skillOfferingId = skillOfferingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
