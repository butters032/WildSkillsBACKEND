package com.teamwiski.wildskills.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwiski.wildskills.Entity.ReviewEntity;
import com.teamwiski.wildskills.Repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository rrepo;

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
}
