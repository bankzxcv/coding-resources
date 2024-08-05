package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.ResourceList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceListDAO extends JpaRepository<ResourceList, Long> {
    ResourceList findById(Integer id);


    List<ResourceList> findByListId(Integer listId);
}
