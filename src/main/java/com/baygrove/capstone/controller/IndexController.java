package com.baygrove.capstone.controller;

import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.ResourceListDAO;
import com.baygrove.capstone.database.dao.UserListDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.ResourceList;
import com.baygrove.capstone.database.entity.User;
import com.baygrove.capstone.database.entity.UserList;
import com.baygrove.capstone.dto.ResourceDTO;
import com.baygrove.capstone.security.AuthenticatedUserUtilities;
import com.baygrove.capstone.service.ResourceService;
import com.baygrove.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class IndexController {
    @Autowired
    ResourceDAO resourceDAO;

    @Autowired
    UserListDAO userListDAO;

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    ResourceListDAO resourceListDAO;


    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView response = new ModelAndView("index");

        List<Resource> resources = resourceDAO.findAll();
        List<ResourceDTO> resourceDTOs = new ArrayList<>();
        
        Integer userListId = userService.getCurrentUserDefaultListId();
        List<ResourceList> resourceLists = resourceListDAO.findByListId(userListId);

        Set<Integer> set = new HashSet<>();
        for (ResourceList resourceList : resourceLists) {
            set.add(resourceList.getResourceId());
        }

        for (Resource resource : resources) {
            boolean isAdded = set.contains(resource.getId());

            ResourceDTO resourceDTO = resourceService.convertResourceToResourceDTO(resource, isAdded);
            resourceDTOs.add(resourceDTO);
        }

        response.addObject("resources", resourceDTOs);
        response.addObject("userListId", userListId);

        return response;
    }
}
