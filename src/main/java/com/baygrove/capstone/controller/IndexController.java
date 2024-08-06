package com.baygrove.capstone.controller;

import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.UserListDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.User;
import com.baygrove.capstone.database.entity.UserList;
import com.baygrove.capstone.security.AuthenticatedUserUtilities;
import com.baygrove.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class IndexController {
    @Autowired
    ResourceDAO resourceDAO;

    @Autowired
    UserListDAO userListDAO;


    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView response = new ModelAndView("index");

        List<Resource> resources = resourceDAO.findAll();

        response.addObject("resources", resources);
        response.addObject("userListId", userService.getCurrentUserDefaultListId());

        return response;
    }
}
