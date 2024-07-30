package com.baygrove.capstone.service;


import com.baygrove.capstone.database.dao.UserDAO;
import com.baygrove.capstone.database.entity.User;
import com.baygrove.capstone.form.CreateUserFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateUserFormBean form) {
        User user = new User();
        user.setEmail(form.getEmail());
        String encryptedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encryptedPassword);
        user.setCreatedAt(new Date());
        userDAO.save(user);

        return user;
    }
}
