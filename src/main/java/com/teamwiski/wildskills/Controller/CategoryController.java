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

import com.teamwiski.wildskills.Entity.CategoryEntity;
import com.teamwiski.wildskills.Service.CategoryService;

@RestController
@RequestMapping(method=RequestMethod.GET,path="/api/wildSkills/category")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {
    @Autowired
    CategoryService categoryserv;

    //Create
    @PostMapping("/postCategoryRecord")
    public CategoryEntity postCategoryRecord(@RequestBody CategoryEntity category){
        return categoryserv.postCategoryRecord(category);
    }
    //Read
    @GetMapping("/getAllCategory")
    public List<CategoryEntity>getAllCategory(){
        return categoryserv.getAllCategory();
    }

    //Read by ID
    @GetMapping("/getCategory/{id}")
    public CategoryEntity getCategoryById(@PathVariable int id) {
        return categoryserv.getCategoryById(id); 
    }
    //Update
    @PutMapping("/putCategoryDetails")
    public CategoryEntity putCategoryDetails(@RequestParam int id, @RequestBody CategoryEntity newCategoryDetails ){
        return categoryserv.putCategoryDetails(id,newCategoryDetails);
    }
    
    //Delete
    @DeleteMapping("/deleteCourseDetails/{id}")
    public String deleteCategory(@PathVariable int id){
        return categoryserv.deleteCategory(id);
    }

}
