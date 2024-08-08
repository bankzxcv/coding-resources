package com.baygrove.capstone.controller;


import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.ResourceTopic;
import com.baygrove.capstone.database.entity.Topic;
import com.baygrove.capstone.database.enums.ResourceStatus;
import com.baygrove.capstone.dto.ResourceDTO;
import com.baygrove.capstone.form.ResourceFormBean;
import com.baygrove.capstone.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private ResourceService resourceService;

    @GetMapping("/topics/{id}")
    public ModelAndView resourcesByTopic(@PathVariable(name = "id") Integer topicId) {
        ModelAndView response = new ModelAndView("resource/resources-by-topic");

        List<Resource> resources = resourceDAO.findByTopicId(topicId);
        Topic topic = topicDAO.findById(topicId);

        List<ResourceDTO> resourceDTOs = resourceService.convertResourcesToResourceDTOsWithIsAddedProperty(resources);
        response.addObject("resources", resourceDTOs);

        response.addObject("topicName", topic.getName());
        return response;
    }


    @GetMapping("/detail/{id}")
    public ModelAndView resourceDetail(@PathVariable(name = "id") Integer resourceId) {
        ModelAndView response = new ModelAndView("resource/resource-detail");

        log.info("resourceId: " + resourceId);

        Resource resource = resourceDAO.findById(resourceId);

        response.addObject("resource", resource);
        return response;
    }

    @GetMapping("search")
    public ModelAndView searchResource(@RequestParam(required = false) String search) {
        ModelAndView response = new ModelAndView("index");

        List<Resource> resources = resourceDAO.findByNameLike(search);

        List<ResourceDTO> resourceDTOs = resourceService.convertResourcesToResourceDTOsWithIsAddedProperty(resources);
        response.addObject("resources", resourceDTOs);

        response.addObject("searchTerm", search);


        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("edit")
    public ModelAndView getAdminEditResource(@RequestParam Integer resourceId) {
        ModelAndView response = new ModelAndView("admin/resource-form");
        Resource resource = resourceDAO.findById(resourceId);

        ResourceFormBean form = new ResourceFormBean();

        BeanUtils.copyProperties(resource, form);

        List<Integer> topicIds = resource.getResourceTopics().stream().map(resourceTopic -> resourceTopic.getTopicId()).toList();
        form.setTopicIds(topicIds);

        List<Integer> categoryIds = resource.getResourceCategories().stream().map(resourceCategory -> resourceCategory.getCategoryId()).toList();
        form.setCategoryIds(categoryIds);

        response.addObject("form", form);

        List<Topic> topics = topicDAO.findAllByOrderByNameAsc();
        response.addObject("topics", topics);
        List<ResourceStatus> statuses = new ArrayList<>();
        statuses.add(ResourceStatus.Pending);
        statuses.add(ResourceStatus.Active);
        response.addObject("statuses", statuses);
        return response;
    }
}
