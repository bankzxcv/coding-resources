package com.baygrove.capstone.controller;

import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.entity.Topic;
import com.baygrove.capstone.dto.ResourceDTO;
import com.baygrove.capstone.service.ResourceService;
import com.baygrove.capstone.utils.resources.ResourceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ResourceControllerTest {

    @Mock
    private ResourceDAO resourceDAO;

    @Mock
    private TopicDAO topicDAO;

    @Mock
    private ResourceService resourceService;

    @Mock
    private ResourceUtils resourceUtils;

    @InjectMocks
    private ResourceController resourceController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();
    }

    @Test
    void resourcesByTopic_ShouldReturnResourcesForTopic() throws Exception {
        // Arrange
        Integer topicId = 1;
        Topic topic = new Topic();
        topic.setId(topicId);
        topic.setName("Java");

        Resource resource = new Resource();
        resource.setId(1);
        resource.setName("Java Tutorial");
        List<Resource> resources = Arrays.asList(resource);

        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(1);
        resourceDTO.setName("Java Tutorial");
        List<ResourceDTO> resourceDTOs = Arrays.asList(resourceDTO);

        when(resourceDAO.findPublishResourcesByTopicId(topicId)).thenReturn(resources);
        when(topicDAO.findById(topicId)).thenReturn(topic);
        when(resourceUtils.convertResourcesToResourceDTOsWithIsAddedProperty(resources)).thenReturn(resourceDTOs);

        // Act
        ModelAndView result = resourceController.resourcesByTopic(topicId);

        // Assert
        assertEquals("resource/resources-by-topic", result.getViewName());
        assertEquals(resourceDTOs, result.getModel().get("resources"));
        
        verify(resourceDAO).findPublishResourcesByTopicId(topicId);
        verify(topicDAO).findById(topicId);
        verify(resourceUtils).convertResourcesToResourceDTOsWithIsAddedProperty(resources);
    }

    @Test
    void resourcesByTopic_WhenNoResources_ShouldReturnEmptyList() throws Exception {
        // Arrange
        Integer topicId = 1;
        Topic topic = new Topic();
        topic.setId(topicId);
        topic.setName("Java");

        when(resourceDAO.findPublishResourcesByTopicId(topicId)).thenReturn(List.of());
        when(topicDAO.findById(topicId)).thenReturn(topic);
        when(resourceUtils.convertResourcesToResourceDTOsWithIsAddedProperty(List.of())).thenReturn(List.of());

        // Act
        ModelAndView result = resourceController.resourcesByTopic(topicId);

        // Assert
        assertEquals("resource/resources-by-topic", result.getViewName());
        assertEquals(0, ((List<?>) result.getModel().get("resources")).size());
        
        verify(resourceDAO).findPublishResourcesByTopicId(topicId);
        verify(topicDAO).findById(topicId);
        verify(resourceUtils).convertResourcesToResourceDTOsWithIsAddedProperty(List.of());
    }

    @Test
    void resourcesByTopic_WhenTopicNotFound_ShouldHandleGracefully() throws Exception {
        // Arrange
        Integer topicId = 999;
        when(resourceDAO.findPublishResourcesByTopicId(topicId)).thenReturn(List.of());
        when(topicDAO.findById(topicId)).thenReturn(null);
        when(resourceUtils.convertResourcesToResourceDTOsWithIsAddedProperty(List.of())).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/resources/topics/" + topicId))
                .andExpect(status().isOk())
                .andExpect(view().name("resource/resources-by-topic"))
                .andExpect(model().attributeExists("resources"))
                .andExpect(model().attribute("resources", List.of()))
                .andExpect(model().attribute("topicName", "Topic Not Found"));

        verify(resourceDAO).findPublishResourcesByTopicId(topicId);
        verify(topicDAO).findById(topicId);
        verify(resourceUtils).convertResourcesToResourceDTOsWithIsAddedProperty(List.of());
    }
}
