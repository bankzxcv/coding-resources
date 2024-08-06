package com.baygrove.capstone.controller;


import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.ResourceListDAO;
import com.baygrove.capstone.database.dao.UserDAO;
import com.baygrove.capstone.database.dao.UserListDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.ResourceList;
import com.baygrove.capstone.database.entity.UserList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/user-list")
public class UserListController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserListDAO userListDAO;

    @Autowired
    private ResourceDAO resourceDAO;

    @Autowired
    private ResourceListDAO resourceListDAO;

    @GetMapping("/add-resource")
    public ModelAndView addResourceToUserList(@RequestParam Integer resourceId, @RequestParam(required = false) Integer userListId) {
        ModelAndView response = new ModelAndView("index");

        if (userListId == null) {
            response.setViewName("redirect:/auth/login");
            return response;
        }

        UserList userList = userListDAO.findById(userListId);
        Resource resource = resourceDAO.findById(resourceId);

        ResourceList resourceList = new ResourceList();
        resourceList.setUserList(userList);
        resourceList.setResource(resource);

        resourceListDAO.save(resourceList);

        return response;
    }
}
