package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceDAO extends JpaRepository<Resource, Long> {
    Resource findById(Integer id);

    @Query("from Resource")
    @Override
    List<Resource> findAll();

}