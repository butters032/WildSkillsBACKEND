package com.teamwiski.wildskills.Repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamwiski.wildskills.Entity.SkillExchangeEntity;

@Repository
public interface SkillExchangeRepository extends JpaRepository<SkillExchangeEntity, Integer>{
	//public List<SkillExchangeEntity> findByStudentStudentId(int studentId);
}
