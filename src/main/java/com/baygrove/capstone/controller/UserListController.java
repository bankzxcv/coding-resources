package com.baygrove.capstone.controller;

import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.ResourceListDAO;
import com.baygrove.capstone.database.dao.UserListDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.ResourceList;
import com.baygrove.capstone.database.entity.UserList;
import com.baygrove.capstone.dto.ResourceDTO;
import com.baygrove.capstone.service.ResourceService;
import com.baygrove.capstone.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user-list")
public class UserListController {
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

    @GetMapping("/add-resource")
    public ModelAndView addResourceToUserList(@RequestParam Integer resourceId, @RequestParam(required = false) Integer userListId, HttpServletRequest request) {
        ModelAndView response = new ModelAndView();

        if (userListId == null) {
            response.setViewName("redirect:/auth/login");
            return response;
        }

        UserList userList = userListDAO.findById(userListId);
        Resource resourceToAdd = resourceDAO.findById(resourceId);

        ResourceList newResourceList = new ResourceList();
        newResourceList.setUserList(userList);
        newResourceList.setResource(resourceToAdd);

        resourceListDAO.save(newResourceList);

        // redirect to previous page
        String referrer = request.getHeader("referer");
        response.setViewName("redirect:" + referrer);

        return response;
    }

    @GetMapping("/remove-resource")
    public ModelAndView removeResourceFromUserList(@RequestParam Integer resourceId, @RequestParam Integer userListId, HttpServletRequest request) {
        ModelAndView response = new ModelAndView();

        ResourceList resourceList = resourceListDAO.findByListIdAndResourceId(userListId, resourceId);
        resourceListDAO.delete(resourceList);

        // redirect to previous page
        String referrer = request.getHeader("referer");
        response.setViewName("redirect:" + referrer);

        return response;
    }

    @GetMapping("all")
    public ModelAndView getUserResources() {
        ModelAndView response = new ModelAndView("user/all-lists");

        Integer userListId = userService.getCurrentUserDefaultListId();
        List<ResourceList> resourceLists = resourceListDAO.findByListId(userListId);

        List<Integer> resourceIds = resourceLists.stream().map(resourceList -> resourceList.getResourceId()).toList();
        List<Resource> resources = resourceDAO.findByIds(resourceIds);

        List<ResourceDTO> resourceDTOs = resourceService.convertResourcesToResourceDTOsWithIsAddedProperty(resources);
        response.addObject("resources", resourceDTOs);

        return response;
    }
}
