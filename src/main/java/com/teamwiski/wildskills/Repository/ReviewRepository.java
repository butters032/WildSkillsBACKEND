package com.teamwiski.wildskills.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teamwiski.wildskills.Entity.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
	
	@Query(value = "SELECT AVG(r.rating) FROM tblreview r WHERE r.reviewee_id = :reviewee_id", nativeQuery = true)
    double findAverageRatingByStudentId(int reviewee_id);
	
	@Query(value = "SELECT student_id FROM exchange_student WHERE skill_exchangeid = :exchange_id AND student_id != :reviewer_id", nativeQuery = true) 
    int findReviewee(int reviewer_id, int exchange_id);
}
