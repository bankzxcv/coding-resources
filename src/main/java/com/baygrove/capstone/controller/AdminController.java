package com.baygrove.capstone.controller;


import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Topic;
import com.baygrove.capstone.database.entity.User;
import com.baygrove.capstone.security.AuthenticatedUserUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AuthenticatedUserUtilities authenticatedUserUtilities;

    @Autowired
    TopicDAO topicDAO;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/dashboard")
    public ModelAndView adminDashboard() {
        ModelAndView response = new ModelAndView("admin/dashboard");

        User user = authenticatedUserUtilities.getCurrentUser();

        log.info("user: " + user.toString());
        return response;
    }


    @GetMapping("add-new-resource")
    public ModelAndView addNewResource() {
        ModelAndView response = new ModelAndView("admin/resource-form");
        List<Topic> topics = topicDAO.findAllByOrderByNameAsc();

        response.addObject("topics", topics);
        return response;
    }
}