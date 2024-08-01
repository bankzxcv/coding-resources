package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.ResourceTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceTopicDAO extends JpaRepository<ResourceTopic, Long> {
    ResourceTopic findById(Integer id);

    List<ResourceTopic> findByResourceId(Integer id);
}
