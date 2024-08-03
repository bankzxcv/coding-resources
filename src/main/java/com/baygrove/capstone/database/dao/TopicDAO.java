package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.Topic;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicDAO extends JpaRepository<Topic, Long> {
    Topic findById(Integer id);

    @Nonnull
    List<Topic> findAll();

    List<Topic> findAllByOrderByIdAsc();
    
    List<Topic> findAllByOrderByNameAsc();
}
