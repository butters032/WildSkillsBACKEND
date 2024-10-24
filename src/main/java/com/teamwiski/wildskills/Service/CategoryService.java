package com.teamwiski.wildskills.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.CategoryEntity;
import com.teamwiski.wildskills.Repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository crepo;

    public CategoryService(){
        super();
    }
    //Create
    public CategoryEntity postCategoryRecord(CategoryEntity category){
        return crepo.save(category);
    }

    //Read
    public List<CategoryEntity>getAllCategory(){
        return crepo.findAll();
    }

    //Update
    @SuppressWarnings("finally")
    public CategoryEntity putCategoryDetails(int id,CategoryEntity newCategoryDetails){
        CategoryEntity category=new CategoryEntity();
        try {           
            category=crepo.findById(id).get();

            category.setName(newCategoryDetails.getName());
        } catch (NoSuchElementException nex) {
            throw new NameNotFoundException("Category" + id + "not found");
        }finally{
            return crepo.save(category);
        }
    }

    //Delete
    public String deleteCategory(int id) {
        String msg = "";        
        
        if (crepo.findById(id).isPresent()) {
            crepo.deleteById(id); 
            msg = "Cateogry deleted!";
        } else {
            msg = id + " NOT FOUND";
        }
        
        return msg;
    }
}