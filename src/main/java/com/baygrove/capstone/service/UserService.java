package com.baygrove.capstone.service;


import com.baygrove.capstone.database.dao.UserDAO;
import com.baygrove.capstone.database.dao.UserListDAO;
import com.baygrove.capstone.database.dao.UserRoleDAO;
import com.baygrove.capstone.database.entity.User;
import com.baygrove.capstone.database.entity.UserList;
import com.baygrove.capstone.database.entity.UserRole;
import com.baygrove.capstone.form.UserFormBean;
import com.baygrove.capstone.security.AuthenticatedUserUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    UserListDAO userListDAO;

    @Autowired
    AuthenticatedUserUtilities authenticatedUserUtilities;


    public UserRole createUserRole(Integer userId, String roleName) {
        UserRole userRole = new UserRole();

        userRole.setUserId(userId);
        userRole.setRoleName(roleName);
        userRole.setCreateDate(new Date());

        userRoleDAO.save(userRole);

        return userRole;
    }

    public User createUser(UserFormBean form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setUsername(form.getUsername());

        String encryptedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encryptedPassword);

        user.setCreatedAt(new Date());
        userDAO.save(user);

        // create default user list
        UserList userList = new UserList();
        userList.setName("My List");
        userList.setUser(user);

        userListDAO.save(userList);

        return user;
    }

    public Integer getCurrentUserDefaultListId() {
        User user = authenticatedUserUtilities.getCurrentUser();

        if (user == null) {
            return null;
        }

        List<UserList> userLists = userListDAO.findByUserId(user.getId());

        if (userLists.size() == 0) {
            UserList userList = new UserList();
            userList.setName("My List");
            userList.setUser(user);

            userListDAO.save(userList);
            return userList.getId();
        }

        return userLists.get(0).getId();
    }
}
