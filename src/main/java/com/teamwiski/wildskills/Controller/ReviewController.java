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

import com.teamwiski.wildskills.Entity.ReviewEntity;
import com.teamwiski.wildskills.Service.ReviewService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/api/wildSkills/review")
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewController {
    @Autowired
    ReviewService reviewServ;

    // Create
    @PostMapping("/postReviewRecord")
    public ReviewEntity postReviewRecord(@RequestBody ReviewEntity review) {
        return reviewServ.postReviewRecord(review);
    }

    // Read
    @GetMapping("/getAllReviews")
    public List<ReviewEntity> getAllReviews() {
        return reviewServ.getAllReviews();
    }
    
    // Read by ID
    @GetMapping("/getReviewById/{id}")
    public ReviewEntity getReviewById(@PathVariable int id) {
        return reviewServ.getReviewById(id);
    }

    // Update
    @PutMapping("/putReviewDetails/{id}")
    public ReviewEntity putReviewDetails(@PathVariable int id, @RequestBody ReviewEntity newReviewDetails) {
        return reviewServ.putReviewDetails(id, newReviewDetails);
    }

    // Delete
    @DeleteMapping("/deleteReviewDetails/{id}")
    public String deleteReview(@PathVariable int id) {
        return reviewServ.deleteReview(id);
    }
}