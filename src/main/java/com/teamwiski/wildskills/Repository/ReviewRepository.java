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
}
