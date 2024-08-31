package com.baygrove.capstone.service;


import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    TopicDAO topicDAO;

    @Autowired
    TopicService(TopicDAO topicDAO) {
        this.topicDAO = topicDAO;
    }

    public List<Topic> getAllTopicsByOrderByNameAsc() {
        return topicDAO.findAllByOrderByNameAsc();
    }
}
