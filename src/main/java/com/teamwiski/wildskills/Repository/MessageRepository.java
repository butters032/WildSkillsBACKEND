package com.teamwiski.wildskills.Repository;

import java.util.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teamwiski.wildskills.Entity.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    @Query("SELECT m FROM MessageEntity m WHERE m.chat.chatId = :chatId ORDER BY m.timeStamp ASC")
    List<MessageEntity> findMessagesByChatId(@Param("chatId") int chatId);
}