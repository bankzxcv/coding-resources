package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.UserList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserListDAO extends JpaRepository<UserList, Long> {
    UserList findById(Integer id);


    List<UserList> findByUserId(Integer userId);
}
