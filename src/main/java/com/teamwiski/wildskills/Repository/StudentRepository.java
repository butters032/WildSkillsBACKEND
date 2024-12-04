package com.teamwiski.wildskills.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teamwiski.wildskills.Entity.ChatEntity;
import com.teamwiski.wildskills.Entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{

    Optional<StudentEntity> findByEmail(String email);
    List<StudentEntity> findByChatsChatId (int chatId);
    
    @Query(value = "SELECT COUNT(*) FROM tblstudent", nativeQuery = true)
    int searchTotalUsers();
}
