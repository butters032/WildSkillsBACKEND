package com.teamwiski.wildskills.Repository;


import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamwiski.wildskills.Entity.SkillExchangeEntity;

@Repository
public interface SkillExchangeRepository extends JpaRepository<SkillExchangeEntity, Integer>{
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM exchange_student WHERE skill_exchangeid = :skill_exchangeid", nativeQuery = true)
    void deleteAssociations(int skill_exchangeid);
}
