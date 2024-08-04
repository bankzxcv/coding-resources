package com.baygrove.capstone.service;

import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.ResourceTopicDAO;
import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.ResourceTopic;
import com.baygrove.capstone.database.entity.Topic;
import com.baygrove.capstone.form.ResourceFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ResourceService {

    @Autowired
    ResourceDAO resourceDAO;

    @Autowired
    ResourceTopicDAO resourceTopicDAO;

    @Autowired
    TopicDAO topicDAO;

    public Resource createResource(ResourceFormBean form) {
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

        return resource;
    }
}
