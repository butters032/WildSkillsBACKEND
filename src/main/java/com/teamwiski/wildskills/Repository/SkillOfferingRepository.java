package com.teamwiski.wildskills.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamwiski.wildskills.Entity.SkillOfferingEntity;

@Repository
public interface SkillOfferingRepository extends JpaRepository<SkillOfferingEntity, Integer>{

}
