package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.Category;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Category, Long> {
    Category findById(Integer id);

    @Nonnull
    List<Category> findAll();

}
