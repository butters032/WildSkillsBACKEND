package com.teamwiski.wildskills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamwiski.wildskills.entity.SkillExchangeEntity;

@Repository
public interface SkillExchangeRepository extends JpaRepository<SkillExchangeEntity, Integer>{
	
}
