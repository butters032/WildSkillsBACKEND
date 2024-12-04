package com.teamwiski.wildskills.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamwiski.wildskills.Entity.SkillExchangeEntity;
import com.teamwiski.wildskills.Entity.SkillOfferingEntity;

@Repository
public interface SkillExchangeRepository extends JpaRepository<SkillExchangeEntity, Integer>{
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM exchange_student WHERE skill_exchangeid = :skill_exchangeid", nativeQuery = true)
    void deleteAssociations(int skill_exchangeid);
    
    @Query(value = "SELECT * FROM tbl_skill_exchange so ORDER BY skill_exchangeid DESC LIMIT 5", nativeQuery = true)
    List<SkillExchangeEntity> searchRecentExchanges();
    
    @Query(value = "SELECT COUNT(*) FROM tbl_skill_exchange", nativeQuery = true)
    int searchTotalExchanges();
}
