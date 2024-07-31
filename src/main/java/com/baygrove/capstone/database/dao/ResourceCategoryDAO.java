package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.ResourceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceCategoryDAO extends JpaRepository<ResourceCategory, Long> {
    ResourceCategory findById(Integer id);

    List<ResourceCategory> findByResourceId(Integer id);
}
