package com.baygrove.capstone.utils.resources;

import com.baygrove.capstone.database.dao.ResourceListDAO;
import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.dao.UserListDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.ResourceList;
import com.baygrove.capstone.database.entity.ResourceTopic;
import com.baygrove.capstone.database.entity.Topic;
import com.baygrove.capstone.database.enums.ResourceStatus;
import com.baygrove.capstone.dto.ResourceDTO;
import com.baygrove.capstone.service.ResourceService;
import com.baygrove.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class ResourceUtils {

    @Autowired
    TopicDAO topicDAO;

    @Autowired
    UserService userService;

    @Autowired
    UserListDAO userListDAO;

    @Autowired
    ResourceListDAO resourceListDAO;

    @Autowired
    ResourceService resourceService;

    public String saveResourceImage(MultipartFile imageFile) {
        String saveProfileImageName = "./src/main/webapp/assets/img/resources/" + imageFile.getOriginalFilename();
        try {
            Files.copy(imageFile.getInputStream(), Paths.get(saveProfileImageName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error("Unable to finish reading file", e);
        }

        String imageUrl = "/assets/img/resources/" + imageFile.getOriginalFilename();

        return imageUrl;
    }

    public List<ResourceTopic> createResourceTopics(Resource resource, List<Integer> topicIds) throws Exception {
        List<ResourceTopic> resourceTopics = new ArrayList<>();

        for (Integer topicId : topicIds) {
            // find topic
            Topic topic = topicDAO.findById(topicId);

            if (topic == null) {
                throw new Exception("Invalid topic id");
            }

            // create resource topic
            ResourceTopic resourceTopic = new ResourceTopic();
            resourceTopic.setResource(resource);
            resourceTopic.setTopic(topic);

            resourceTopics.add(resourceTopic);
        }

        return resourceTopics;
    }

    public ResourceDTO convertResourceToResourceDTO(Resource resource, Integer isAdded) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setIsAdded(isAdded);
        BeanUtils.copyProperties(resource, resourceDTO);

        return resourceDTO;
    }

    public Set<Integer> getCurrentUserAddedResourceIdSet() {
        Integer userListId = userService.getCurrentUserDefaultListId();

        List<ResourceList> resourceLists = resourceListDAO.findByListId(userListId);

        Set<Integer> set = new HashSet<>();
        for (ResourceList resourceList : resourceLists) {
            set.add(resourceList.getResourceId());
        }

        return set;
    }

    public List<ResourceDTO> convertResourcesToResourceDTOsWithIsAddedProperty(List<Resource> resources) {
        List<ResourceDTO> resourceDTOs = new ArrayList<>();
        Set<Integer> set = getCurrentUserAddedResourceIdSet();

        for (Resource resource : resources) {
            boolean isAdded = set.contains(resource.getId());

            ResourceDTO resourceDTO = convertResourceToResourceDTO(resource, isAdded ? 1 : 0);
            resourceDTOs.add(resourceDTO);
        }

        return resourceDTOs;
    }

    public List<ResourceDTO> getAllResourceDTOs() {
        List<Resource> resources = resourceService.getAllResources();
        return convertResourcesToResourceDTOsWithIsAddedProperty(resources);
    }

    public void addResourceFormOptions(ModelAndView response) {
        List<Topic> topics = topicDAO.findAllByOrderByNameAsc();
        response.addObject("topics", topics);

        List<ResourceStatus> statuses = new ArrayList<>();
        statuses.add(ResourceStatus.Pending);
        statuses.add(ResourceStatus.Draft);
        statuses.add(ResourceStatus.Publish);
        statuses.add(ResourceStatus.Private);
        statuses.add(ResourceStatus.Archive);
        response.addObject("statuses", statuses);
    }
}
