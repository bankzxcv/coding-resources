package com.baygrove.capstone.controller;

import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.Topic;
import com.baygrove.capstone.dto.ResourceDTO;
import com.baygrove.capstone.form.ResourceFormBean;
import com.baygrove.capstone.service.ResourceService;
import com.baygrove.capstone.utils.resources.ResourceUtils;
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

import java.util.List;
import java.util.Set;

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

    @Autowired
    private ResourceUtils resourceUtils;

    @GetMapping("/topics/{id}")
    public ModelAndView resourcesByTopic(@PathVariable(name = "id") Integer topicId) {
        ModelAndView response = new ModelAndView("resource/resources-by-topic");

        List<Resource> resources = resourceDAO.findPublishResourcesByTopicId(topicId);
        Topic topic = topicDAO.findById(topicId);

        List<ResourceDTO> resourceDTOs = resourceUtils.convertResourcesToResourceDTOsWithIsAddedProperty(resources);
        response.addObject("resources", resourceDTOs);
        
        // Handle case where topic is not found
        if (topic != null) {
            response.addObject("topicName", topic.getName());
        } else {
            response.addObject("topicName", "Topic Not Found");
        }

        return response;
    }


    @GetMapping("/detail/{id}")
    public ModelAndView resourceDetail(@PathVariable(name = "id") Integer resourceId) {
        ModelAndView response = new ModelAndView("resource/resource-detail");

        log.info("resourceId: " + resourceId);

        Resource resource = resourceDAO.findById(resourceId);

        Set<Integer> addedResourceIdSet = resourceUtils.getCurrentUserAddedResourceIdSet();
        boolean isAdded = addedResourceIdSet.contains(resource.getId());
        ResourceDTO resourceDTO = resourceUtils.convertResourceToResourceDTO(resource, isAdded ? 1 : 0);

        response.addObject("resource", resourceDTO);
        return response;
    }

    @GetMapping("search")
    public ModelAndView searchResource(@RequestParam(required = false) String search) {
        ModelAndView response = new ModelAndView("index");

        List<Resource> resources = resourceDAO.findPublishResourcesByNameLike(search);

        List<ResourceDTO> resourceDTOs = resourceUtils.convertResourcesToResourceDTOsWithIsAddedProperty(resources);
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

        resourceUtils.addResourceFormOptions(response);
        return response;
    }
}
