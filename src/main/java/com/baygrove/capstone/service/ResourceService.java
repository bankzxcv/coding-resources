package com.baygrove.capstone.service;

import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.ResourceListDAO;
import com.baygrove.capstone.database.dao.ResourceTopicDAO;
import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.ResourceTopic;
import com.baygrove.capstone.database.enums.ResourceStatus;
import com.baygrove.capstone.form.ResourceFormBean;
import com.baygrove.capstone.utils.resources.ResourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ResourceService {

    @Autowired
    ResourceDAO resourceDAO;

    @Autowired
    ResourceTopicDAO resourceTopicDAO;

    @Autowired
    TopicDAO topicDAO;

    @Autowired
    UserService userService;

    @Autowired
    ResourceListDAO resourceListDAO;

    @Lazy
    @Autowired
    ResourceUtils resourceUtils;

    @Cacheable(value = "resources")
    public List<Resource> getAllResources() {
        log.info("getAllResources is called!!!");
        return resourceDAO.findByStatus(ResourceStatus.Publish);
    }

    // clear the entire cache when adding or updating resources
    @CacheEvict(value = "resources", allEntries = true)
    public Resource createResource(ResourceFormBean form) throws Exception {
        Resource resource = new Resource();

        resource.setName(form.getName());
        resource.setDescription(form.getDescription());
        resource.setUrl(form.getUrl());
        resource.setCreatedAt(new Date());
        resource.setUpdatedAt(new Date());
        resource.setStatus(ResourceStatus.Pending);

        String imageUrl = resourceUtils.saveResourceImage(form.getImageFile());
        resource.setImageUrl(imageUrl);

        resourceDAO.save(resource);

        List<ResourceTopic> resourceTopics = resourceUtils.createResourceTopics(resource, form.getTopicIds());
        resource.setResourceTopics(resourceTopics);

        resourceDAO.save(resource);

        return resource;
    }

    @CachePut(value = "resource", key = "#form.id")
    @CacheEvict(value = "resources", allEntries = true)
    public Resource updateResource(ResourceFormBean form) throws Exception {
        Resource resource = resourceDAO.findById(form.getId());

        resource.setName(form.getName());
        resource.setDescription(form.getDescription());
        resource.setUrl(form.getUrl());
        resource.setUpdatedAt(new Date());
        resource.setStatus(form.getStatus());

        if (!form.getImageFile().isEmpty()) {

            String imageUrl = resourceUtils.saveResourceImage(form.getImageFile());
            resource.setImageUrl(imageUrl);
        }

        resourceDAO.save(resource);

        List<ResourceTopic> existingResourceTopics = resourceTopicDAO.findByResourceId(resource.getId());
        resourceTopicDAO.deleteAll(existingResourceTopics);

        List<ResourceTopic> resourceTopics = resourceUtils.createResourceTopics(resource, form.getTopicIds());
        resource.setResourceTopics(resourceTopics);

        resourceDAO.save(resource);

        return resource;
    }
}
