package com.baygrove.capstone.controller;


import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.ResourceListDAO;
import com.baygrove.capstone.database.dao.UserDAO;
import com.baygrove.capstone.database.dao.UserListDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.ResourceList;
import com.baygrove.capstone.database.entity.UserList;
import com.baygrove.capstone.dto.ResourceDTO;
import com.baygrove.capstone.service.ResourceService;
import com.baygrove.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user-list")
public class UserListController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserListDAO userListDAO;

    @Autowired
    private ResourceDAO resourceDAO;

    @Autowired
    private ResourceListDAO resourceListDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    public List<ResourceDTO> convertResourceListsToResourceDTOs(List<ResourceList> resourceLists) {
        List<ResourceDTO> resourceDTOs = new ArrayList<>();

        for (ResourceList resourceList : resourceLists) {
            Resource resource = resourceDAO.findById(resourceList.getResourceId());
            ResourceDTO resourceDTO = resourceService.convertResourceToResourceDTO(resource, true);
            resourceDTOs.add(resourceDTO);
        }

        return resourceDTOs;
    }

    @GetMapping("/add-resource")
    public ModelAndView addResourceToUserList(@RequestParam Integer resourceId, @RequestParam(required = false) Integer userListId) {
        ModelAndView response = new ModelAndView("index");

        if (userListId == null) {
            response.setViewName("redirect:/auth/login");
            return response;
        }

        UserList userList = userListDAO.findById(userListId);
        Resource resourceToAdd = resourceDAO.findById(resourceId);

        ResourceList resourceList = new ResourceList();
        resourceList.setUserList(userList);
        resourceList.setResource(resourceToAdd);

        resourceListDAO.save(resourceList);

        List<Resource> resources = resourceDAO.findAll();
        List<ResourceDTO> resourceDTOs = new ArrayList<>();

        for (Resource resource : resources) {
            ResourceDTO resourceDTO = resourceService.convertResourceToResourceDTO(resource, false);
            resourceDTOs.add(resourceDTO);
        }

        response.addObject("resources", resourceDTOs);
        response.addObject("userListId", userListId);

        return response;
    }

    @GetMapping("/remove-resource")
    public ModelAndView removeResourceFromUserList(@RequestParam Integer resourceId, @RequestParam Integer userListId) {
        ModelAndView response = new ModelAndView("index");

        ResourceList resourceList = resourceListDAO.findByListIdAndResourceId(userListId, resourceId);
        resourceListDAO.delete(resourceList);

        List<ResourceList> resourceLists = resourceListDAO.findByListId(userListId);

        response.addObject("userListId", userListId);
        response.addObject("resources", convertResourceListsToResourceDTOs(resourceLists));

        return response;
    }

    @GetMapping("all")
    public ModelAndView getUserResources() {
        ModelAndView response = new ModelAndView("user/all-lists");

        Integer userListId = userService.getCurrentUserDefaultListId();
        List<ResourceList> resourceLists = resourceListDAO.findByListId(userListId);


        response.addObject("userListId", userListId);
        response.addObject("resources", convertResourceListsToResourceDTOs(resourceLists));

        return response;
    }
}
