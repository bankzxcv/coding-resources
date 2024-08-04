package com.baygrove.capstone.controller;


import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.ResourceTopic;
import com.baygrove.capstone.database.entity.Topic;
import com.baygrove.capstone.database.entity.User;
import com.baygrove.capstone.form.ResourceFormBean;
import com.baygrove.capstone.security.AuthenticatedUserUtilities;
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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AuthenticatedUserUtilities authenticatedUserUtilities;

    @Autowired
    TopicDAO topicDAO;

    @Autowired
    ResourceDAO resourceDAO;

    private void addTopicsToReponse(ModelAndView response) {
        List<Topic> topics = topicDAO.findAllByOrderByNameAsc();

        response.addObject("topics", topics);
    }

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
        addTopicsToReponse(response);
        return response;
    }


    @PostMapping("/submit-new-resource")
    public ModelAndView submitNewResource(@Valid ResourceFormBean form, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView();

        log.info("submit-new-resource form: " + form.toString());


        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
            }

            if (form.getImageFile().isEmpty()) {
                bindingResult.rejectValue("imageFile", "imageFile", "Resource image is required");
            }

            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);
            addTopicsToReponse(response);

            response.setViewName("admin/resource-form");
            return response;
        }


        String saveProfileImageName = "./src/main/webapp/assets/img/resources/" + form.getImageFile().getOriginalFilename();
        try {
            Files.copy(form.getImageFile().getInputStream(), Paths.get(saveProfileImageName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error("Unable to finish reading file", e);
        }

        String imageUrl = "/assets/img/resources/" + form.getImageFile().getOriginalFilename();

        Resource resource = new Resource();

        resource.setName(form.getName());
        resource.setDescription(form.getDescription());
        resource.setUrl(form.getUrl());
        resource.setCreatedAt(new Date());
        resource.setUpdatedAt(new Date());
        resource.setImageUrl(imageUrl);
        resource.setStatus("Pending"); // TODO: use Enum
        resourceDAO.save(resource);

        List<ResourceTopic> resourceTopics = new ArrayList<>();

        for (Integer topicId : form.getTopicIds()) {
            // find topic
            Topic topic = topicDAO.findById(topicId);

            // create resource topic
            ResourceTopic resourceTopic = new ResourceTopic();
            resourceTopic.setResource(resource);
            resourceTopic.setTopic(topic);

            resourceTopics.add(resourceTopic);
        }

        resource.setResourceTopics(resourceTopics);
        resourceDAO.save(resource);

        response.setViewName("redirect:/");
        return response;
    }
}