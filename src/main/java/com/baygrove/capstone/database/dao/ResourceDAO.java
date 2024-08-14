package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.enums.ResourceStatus;
import com.baygrove.capstone.dto.ResourceDTO;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ResourceDAO extends JpaRepository<Resource, Long> {
    Resource findById(Integer id);

    @Query(value = "SELECT * FROM resources WHERE id IN :ids", nativeQuery = true)
    List<Resource> findByIds(List<Integer> ids);

    Resource findByName(String name);

    Resource findByUrl(String url);

    @Nonnull
    List<Resource> findAll();

    List<Resource> findByStatus(ResourceStatus status);

    @Query(value = "SELECT r.id, r.name, r.url, r.description, r.image_url, r.status, r.created_at, r.updated_at " +
            "FROM resources r " +
            "JOIN resources_topics rt ON rt.resource_id = r.id AND rt.topic_id = :topicId " +
            "JOIN topics t ON rt.topic_id = t.id;", nativeQuery = true)
    List<Resource> findByTopicId(Integer topicId);

    @Query(value = "SELECT r.id, r.name, r.url, r.description, r.image_url, r.status, r.created_at, r.updated_at " +
            "FROM resources r " +
            "JOIN resources_topics rt ON rt.resource_id = r.id AND rt.topic_id = :topicId " +
            "JOIN topics t ON rt.topic_id = t.id " +
            "WHERE r.status = 'Publish';", nativeQuery = true)
    List<Resource> findPublishResourcesByTopicId(Integer topicId);

    @Query(value = "SELECT *, (SELECT count(*) FROM resources_lists rl WHERE rl.resource_id = r.id AND rl.list_id = :userListId) AS isAdded " +
            "FROM resources r;", nativeQuery = true)
    List<Map<String, Object>> findAllWithIsAddedToUserList(Integer userListId);

    @Query("select r from Resource r where LOWER(r.name) like LOWER(concat('%', :name, '%'))")
    List<Resource> findByNameLike(String name);

    @Query("select r from Resource r where r.status = 'Publish' and LOWER(r.name) like LOWER(concat('%', :name, '%'))")
    List<Resource> findPublishResourcesByNameLike(String name);

}