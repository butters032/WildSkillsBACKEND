package com.teamwiski.wildskills.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tblreview")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int reviewId;

    private double rating;
    private String comment;

    public ReviewEntity() {
        super();
    }

    public ReviewEntity(int reviewId, double rating, String comment) {
        super();
        this.reviewId = reviewId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
