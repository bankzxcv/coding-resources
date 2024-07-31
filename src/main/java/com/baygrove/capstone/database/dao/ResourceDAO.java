package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.Resource;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceDAO extends JpaRepository<Resource, Long> {
    Resource findById(Integer id);

    @Nonnull
    List<Resource> findAll();

}