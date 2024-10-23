package com.teamwiski.wildskills.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tblcategory")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int categoryId;

    private String name;
  
    public CategoryEntity(){
        super();
    }
    public CategoryEntity(int categoryId, String name){
        super();
        this.categoryId=categoryId;
        this.name=name;
    }
    public int getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(int categoryId){
        this.categoryId=categoryId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

}
