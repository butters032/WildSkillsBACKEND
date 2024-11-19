package com.teamwiski.wildskills.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.ReviewEntity;
import com.teamwiski.wildskills.Repository.ReviewRepository;

import com.teamwiski.wildskills.Entity.StudentEntity;
import com.teamwiski.wildskills.Repository.StudentRepository;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository rrepo;
    
    @Autowired
    StudentRepository srepo;

    public ReviewService() {
        super();
    }

    // Create
    public ReviewEntity postReviewRecord(ReviewEntity review) {
        return rrepo.save(review);
    }

    // Read
    public List<ReviewEntity> getAllReviews() {
        return rrepo.findAll();
    }
    
    public ReviewEntity getReviewById(int id) {
        return rrepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Review not found with ID: " + id));
    }

    // Update
    @SuppressWarnings("finally")
    public ReviewEntity putReviewDetails(int id, ReviewEntity newReviewDetails) {
        ReviewEntity review = new ReviewEntity();
        try {
            review = rrepo.findById(id).get();
            review.setRating(newReviewDetails.getRating());
            review.setComment(newReviewDetails.getComment());
        } catch (NoSuchElementException nex) {
            throw new NameNotFoundException("Review " + id + " not found");
        } finally {
            return rrepo.save(review);
        }
    }

    // Delete
    public String deleteReview(int id) {
        String msg = "";

        if (rrepo.findById(id).isPresent()) {
            rrepo.deleteById(id);
            msg = "Review deleted!";
        } else {
            msg = id + " NOT FOUND";
        }

        return msg;
    }
    	
    
    // student - review functions
    
    // get ID
    public List<ReviewEntity> getReviewsByStudentId(int studentId) {
        StudentEntity student = srepo.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        return student.getReviews();
    }

    // Assign a review to a specific student
    public ReviewEntity assignReviewToStudent(int studentId, ReviewEntity review) {
        StudentEntity student = srepo.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        review.setStudent(student);
        return rrepo.save(review);
    }
}
