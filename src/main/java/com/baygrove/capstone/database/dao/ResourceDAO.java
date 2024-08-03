package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.Resource;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceDAO extends JpaRepository<Resource, Long> {
    Resource findById(Integer id);

    @Nonnull
    List<Resource> findAll();

    @Query(value = "SELECT r.id, r.name, r.url, r.description, r.image_url, r.status, r.created_at, r.updated_at " +
            "FROM resources r " +
            "JOIN resources_topics rt ON rt.resource_id = r.id AND rt.topic_id = :topicId " +
            "JOIN topics t ON rt.topic_id = t.id;", nativeQuery = true)
    List<Resource> findByTopicId(Integer topicId);
}