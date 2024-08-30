package com.baygrove.capstone.controller;


import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.enums.ResourceStatus;
import com.baygrove.capstone.form.ResourceFormBean;
import com.baygrove.capstone.security.AuthenticatedUserUtilities;
import com.baygrove.capstone.service.ResourceService;
import com.baygrove.capstone.utils.resources.ResourceUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    ResourceUtils resourceUtils;


    @GetMapping("/dashboard")
    public ModelAndView adminDashboard(@RequestParam(required = false) ResourceStatus status) {
        ModelAndView response = new ModelAndView("admin/dashboard");
        List<Resource> resources;

        if (status == ResourceStatus.Publish) {
            resources = resourceDAO.findByStatus(ResourceStatus.Publish);
        } else if (status == ResourceStatus.Pending) {
            resources = resourceDAO.findByStatus(ResourceStatus.Pending);
        } else if (status == ResourceStatus.Archive) {
            resources = resourceDAO.findByStatus(ResourceStatus.Archive);
        } else {
            resources = resourceDAO.findAll();
        }

        response.addObject("resources", resources);
        response.addObject("isAdmin", true);

        return response;
    }

    @GetMapping("add-new-resource")
    public ModelAndView addNewResource() {
        ModelAndView response = new ModelAndView("admin/resource-form");
        resourceUtils.addResourceFormOptions(response);
        return response;
    }


    @PostMapping("/submit-new-resource")
    public ModelAndView submitNewResource(@Valid ResourceFormBean form, BindingResult bindingResult) throws Exception {
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
            resourceUtils.addResourceFormOptions(response);

            response.setViewName("admin/resource-form");
            return response;
        }

        if (form.getId() == null) {
            resourceService.createResource(form);
        } else {
            resourceService.updateResource(form);
        }

        if (authenticatedUserUtilities.isUserInRole("ADMIN")) {
            response.setViewName("redirect:/admin/dashboard");
        } else {
            response.setViewName("redirect:/");
        }

        return response;
    }
}