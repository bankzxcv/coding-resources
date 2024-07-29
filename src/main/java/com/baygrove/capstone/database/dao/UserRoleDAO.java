package com.baygrove.capstone.database.dao;


import com.baygrove.capstone.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRoleDAO extends JpaRepository<UserRole, Long> {
    UserRole findById(Integer id);

}