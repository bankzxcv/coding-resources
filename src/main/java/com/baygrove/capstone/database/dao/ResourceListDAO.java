package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.ResourceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceListDAO extends JpaRepository<ResourceList, Long> {
    ResourceList findById(Integer id);


    List<ResourceList> findByListId(Integer listId);

    @Query("select rl from ResourceList rl where rl.listId = :listId and rl.resourceId = :resourceId")
    ResourceList findByListIdAndResourceId(Integer listId, Integer resourceId);
}
