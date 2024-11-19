package com.teamwiski.wildskills.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamwiski.wildskills.Entity.ChatEntity;
import com.teamwiski.wildskills.Entity.StudentEntity;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {
     List<ChatEntity> findByStudentStudentId (int studentId);
}