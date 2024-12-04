package com.teamwiski.wildskills.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tblcategory")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int categoryId;

    private String name;

    @OneToMany(fetch=FetchType.LAZY,mappedBy="category",
                cascade=CascadeType.ALL)
    @JsonManagedReference(value = "category")
    private List<SkillOfferingEntity> skillOfferings; 

    public CategoryEntity() {
        super();
    }

    public CategoryEntity(int categoryId, String name) {
        super();
        this.categoryId = categoryId;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SkillOfferingEntity> getSkillOfferings() {
        return skillOfferings;
    }

    public void setSkillOfferings(List<SkillOfferingEntity> skillOfferings) {
        this.skillOfferings = skillOfferings;
    }
}
