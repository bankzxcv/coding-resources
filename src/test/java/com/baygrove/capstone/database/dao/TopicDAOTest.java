package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.Topic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TopicDAOTest {
    @Autowired
    private TopicDAO topicDAO;

    @Test
    @Order(1)
    public void testCreateNewTopic() {
        Topic topic1 = new Topic();
        topic1.setName("topic 1");

        topicDAO.save(topic1);

        Topic topic = topicDAO.findByName("topic 1");

        Assertions.assertNotNull(topic);
        Assertions.assertEquals("topic 1", topic.getName());
    }

    @Test
    @Order(2)
    public void testUpdateTopic() {
        Topic topic = topicDAO.findByName("topic 1");

        Assertions.assertNotNull(topic);
        Assertions.assertEquals("topic 1", topic.getName());


        topic.setName("topic 1 edited name");
        topicDAO.save(topic);

        Topic updatedTopic = topicDAO.findById(topic.getId());

        Assertions.assertNotNull(updatedTopic);
        Assertions.assertEquals("topic 1 edited name", updatedTopic.getName());
    }


    @Test
    @Order(3)
    public void testDeleteTopic() {
        Topic topic = topicDAO.findByName("topic 1 edited name");

        Assertions.assertNotNull(topic);
        Assertions.assertEquals("topic 1 edited name", topic.getName());

        topicDAO.delete(topic);

        Topic expectedTopic = topicDAO.findByName("topic 1 edited name");
        Assertions.assertNull(expectedTopic);
    }
}
