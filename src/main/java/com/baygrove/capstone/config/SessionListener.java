package com.baygrove.capstone.config;

import com.baygrove.capstone.database.dao.TopicDAO;
import com.baygrove.capstone.database.entity.Topic;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class SessionListener implements HttpSessionListener {
    @Autowired
    private TopicDAO topicDAO;

    @EventListener
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        List<Topic> topics = topicDAO.findAllByOrderByNameAsc();
        session.setAttribute("topics", topics);
    }
}
