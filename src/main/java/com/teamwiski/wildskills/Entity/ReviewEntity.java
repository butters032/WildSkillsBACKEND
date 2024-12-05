package com.teamwiski.wildskills.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	    @JoinColumn(name = "reviewee_id", referencedColumnName = "student_Id")
	    @JsonBackReference(value="reviewee")
	    private StudentEntity reviewee;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "reviewer_id")
	    @JsonBackReference(value="reviewer")
	    private StudentEntity reviewer;
	    
	    private int revieweeID; // testing
	    
	    private String revieweeName;
	    private String reviewerName;
	
	    public ReviewEntity() {
	        super();
	    }
	    
	    public ReviewEntity(int reviewId, double rating, String comment, StudentEntity reviewee, StudentEntity reviewer) {
	        super();
	        this.reviewId = reviewId;
	        this.rating = rating;
	        this.comment = comment;
	        
	  
	        this.reviewee = reviewee;
	        this.reviewer = reviewer;
	        
	        this.revieweeID = 1;
	        
	        this.revieweeName = null;
	        this.reviewerName = null;
	    }
	    
	    public int getRevieweeID() {
			return revieweeID;
		}

		public void setRevieweeID(int revieweeID) {
			this.revieweeID = revieweeID;
		}

		// testing
	    public void setRevieweeId(int revieweeID) {
			this.revieweeID = revieweeID;
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
	    
	    public StudentEntity getReviewee() {
			return reviewee;
		}
	
		public StudentEntity getReviewer() {
			return reviewer;
		}
	
		public void setReviewee(StudentEntity reviewee) {
	    	this.reviewee = reviewee;
	    }
	    
	    public void setReviewer(StudentEntity reviewer) {
	    	this.reviewer = reviewer;
	    }
	
		public String getRevieweeName() {
			return revieweeName;
		}
	
		public void setRevieweeName(String revieweeName) {
			this.revieweeName = revieweeName;
		}
	
		public String getReviewerName() {
			return reviewerName;
		}
	
		public void setReviewerName(String reviewerName) {
			this.reviewerName = reviewerName;
		}
	}
