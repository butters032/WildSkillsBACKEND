package com.teamwiski.wildskills.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblreview")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int reviewId;

    private double rating;
    private String comment;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private StudentEntity student;

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
    
    public void setStudent(StudentEntity student) {
    	this.student = student;
    }
}
