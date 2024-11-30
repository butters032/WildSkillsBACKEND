package com.teamwiski.wildskills.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tblskilloffering")
public class SkillOfferingEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name = "skill_offering_id")
    private int skillOfferingId;

    private String title;
    private boolean isActive;
    private String description;
    private String skills;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "offering", cascade = CascadeType.ALL)
    private List<SkillExchangeEntity> exchanges;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="categoryID")
    @JsonBackReference
  
    private CategoryEntity category; 

    @ManyToOne
	@JoinColumn (name = "student_id", referencedColumnName = "student_id")
	@JsonIgnore

    private StudentEntity student;
   


    public SkillOfferingEntity() {
        super();
    }

    public SkillOfferingEntity(int skillOfferingId, String title, boolean isActive, String description,StudentEntity student, String skills) {
        super();
        this.skillOfferingId = skillOfferingId;
        this.title = title;
        this.isActive = isActive;
        this.description = description;
        this.skills = skills;
        this.student=student;        
    }
    public Integer getCategoryId() {
        return category != null ? category.getCategoryId() : null;
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

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }
}