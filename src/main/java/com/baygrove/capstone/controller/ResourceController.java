package com.baygrove.capstone.controller;


import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.Topic;
import com.baygrove.capstone.dto.ResourceDTO;
import com.baygrove.capstone.service.ResourceService;
import com.baygrove.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    private ResourceDAO resourceDAO;

    @Autowired
    private TopicDAO topicDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/topics/{id}")
    public ModelAndView resourcesByTopic(@PathVariable(name = "id") Integer topicId) {
        ModelAndView response = new ModelAndView("resource/resources-by-topic");

        List<Resource> resources = resourceDAO.findByTopicId(topicId);
        Topic topic = topicDAO.findById(topicId);

        List<ResourceDTO> resourceDTOs = new ArrayList<>();

        for (Resource resource : resources) {
            ResourceDTO resourceDTO = resourceService.convertResourceToResourceDTO(resource, false);
            resourceDTOs.add(resourceDTO);
        }

        response.addObject("resources", resourceDTOs);
        response.addObject("topicName", topic.getName());
        response.addObject("userListId", userService.getCurrentUserDefaultListId());


        return response;
    }


    @GetMapping("/detail/{id}")
    public ModelAndView resourceDetail(@PathVariable(name = "id") Integer resourceId) {
        ModelAndView response = new ModelAndView("resource/resource-detail");

        log.info("resourceId: " + resourceId);

        Resource resource = resourceDAO.findById(resourceId);


        response.addObject("resource", resource);
        response.addObject("userListId", userService.getCurrentUserDefaultListId());


        return response;
    }
}
