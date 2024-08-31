package com.baygrove.capstone.controller;

import com.baygrove.capstone.database.entity.Topic;
import com.baygrove.capstone.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicRestController {

    private TopicService topicService;

    @Autowired
    public TopicRestController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopicsByOrderByNameAsc();
    }
}
