package com.teamwiski.wildskills.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamwiski.wildskills.Entity.AuthenticationEntity;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity, Integer>{

}