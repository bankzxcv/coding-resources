package com.baygrove.capstone.controller;


import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.Topic;
import com.baygrove.capstone.database.entity.User;
import com.baygrove.capstone.dto.ResourceDTO;
import com.baygrove.capstone.form.ResourceFormBean;
import com.baygrove.capstone.security.AuthenticatedUserUtilities;
import com.baygrove.capstone.service.ResourceService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AuthenticatedUserUtilities authenticatedUserUtilities;

    @Autowired
    TopicDAO topicDAO;

    @Autowired
    ResourceDAO resourceDAO;

    @Autowired
    ResourceService resourceService;

    private void addTopicsToReponse(ModelAndView response) {
        List<Topic> topics = topicDAO.findAllByOrderByNameAsc();

        response.addObject("topics", topics);
    }

    @GetMapping("/dashboard")
    public ModelAndView adminDashboard() {
        ModelAndView response = new ModelAndView("admin/dashboard");


        List<Resource> resources = resourceDAO.findAll();
        response.addObject("resources", resources);
        response.addObject("isAdmin", true);

        return response;
    }

    @GetMapping("add-new-resource")
    public ModelAndView addNewResource() {
        ModelAndView response = new ModelAndView("admin/resource-form");
        addTopicsToReponse(response);
        return response;
    }

    @PostMapping("/submit-new-resource")
    public ModelAndView submitNewResource(@Valid ResourceFormBean form, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView();

        log.info("submit-new-resource form: " + form);

        if (form.getId() == null) {
            Resource resourceWithThisName = resourceDAO.findByName(form.getName());

            if (resourceWithThisName != null) {
                bindingResult.rejectValue("name", "name", "This resource name is already in the system.");
            }

            Resource resourceWithThisUrl = resourceDAO.findByUrl(form.getUrl());

            if (resourceWithThisUrl != null) {
                bindingResult.rejectValue("url", "url", "This resource url is already in the system.");
            }
        }

        boolean noImageOnNewResource = form.getImageUrl() == null && form.getImageFile().isEmpty();

        if (noImageOnNewResource || bindingResult.hasErrors()) {
            if (noImageOnNewResource) {
                bindingResult.rejectValue("imageFile", "imageFile", "Resource image is required.");
            }

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
            }

            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);
            addTopicsToReponse(response);

            response.setViewName("admin/resource-form");
            return response;
        }

        if (form.getId() == null) {
            resourceService.createResource(form);
        } else {
            resourceService.editResource(form);
        }

        if (authenticatedUserUtilities.isUserInRole("ADMIN")) {
            response.setViewName("redirect:/admin/dashboard");
        } else {
            response.setViewName("redirect:/");
        }

        return response;
    }
}