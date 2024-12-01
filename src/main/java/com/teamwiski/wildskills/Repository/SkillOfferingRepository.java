package com.teamwiski.wildskills.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teamwiski.wildskills.Entity.SkillOfferingEntity;

@Repository
public interface SkillOfferingRepository extends JpaRepository<SkillOfferingEntity, Integer>{
    public List<SkillOfferingEntity> findByStudentStudentId(int studentId);

    @Query(value = "SELECT * FROM tblskilloffering so WHERE " + 
        "LOWER(so.title) LIKE LOWER(CONCAT('%',:query,'%'))" + 
        "OR LOWER(so.description) LIKE LOWER(CONCAT('%',:query,'%'))", nativeQuery = true)
    List<SkillOfferingEntity> searchOfferingsSQL(String query);

    @Query(value = "SELECT * FROM tblskilloffering so " +
        "JOIN tblcategory c ON so.categoryid = c.category_id " +
        "WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<SkillOfferingEntity> searchOfferingsByCategorySQL(String query);
}
