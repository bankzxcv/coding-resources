package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<User, Long> {
    User findById(Integer id);


    User findByEmailIgnoreCase(String email);
}
