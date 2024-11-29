package com.baygrove.capstone.controller;

import com.baygrove.capstone.database.entity.Topic;
import com.baygrove.capstone.service.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class TopicRestControllerTest {

    @Mock
    private TopicService topicService;

    @InjectMocks
    private TopicRestController topicRestController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(topicRestController).build();
    }

    @Test
    void getAllTopics_ShouldReturnListOfTopics() throws Exception {
        // Arrange
        Topic topic1 = new Topic();
        topic1.setId(1);
        topic1.setName("Java");

        Topic topic2 = new Topic();
        topic2.setId(2);
        topic2.setName("Python");

        List<Topic> topics = Arrays.asList(topic1, topic2);
        when(topicService.getAllTopicsByOrderByNameAsc()).thenReturn(topics);

        // Act & Assert
        mockMvc.perform(get("/api/topics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Java")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Python")));

        verify(topicService, times(1)).getAllTopicsByOrderByNameAsc();
    }

    @Test
    void getAllTopics_WhenNoTopics_ShouldReturnEmptyList() throws Exception {
        // Arrange
        when(topicService.getAllTopicsByOrderByNameAsc()).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/api/topics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(topicService, times(1)).getAllTopicsByOrderByNameAsc();
    }
}
