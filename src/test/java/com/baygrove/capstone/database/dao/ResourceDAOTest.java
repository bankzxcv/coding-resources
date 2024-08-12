package com.baygrove.capstone.database.dao;


import com.baygrove.capstone.database.entity.Resource;
import com.baygrove.capstone.database.enums.ResourceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ResourceDAOTest {
    @Autowired
    private ResourceDAO resourceDAO;

    @Test
    @Order(1)
    public void testCreateNewResource() {
        Resource resource1 = new Resource();
        resource1.setName("Resource 1");
        resource1.setUrl("Resource 1 url");
        resource1.setDescription("Resource 1 description");
        resource1.setImageUrl("Resource 1 image url");
        resource1.setCreatedAt(new Date());
        resource1.setUpdatedAt(new Date());
        resource1.setStatus(ResourceStatus.Pending);

        resourceDAO.save(resource1);

        Resource resource = resourceDAO.findByName("Resource 1");

        Assertions.assertNotNull(resource);
        Assertions.assertEquals("Resource 1", resource.getName());
        Assertions.assertEquals("Resource 1 url", resource.getUrl());
        Assertions.assertEquals("Resource 1 description", resource.getDescription());
        Assertions.assertEquals("Resource 1 image url", resource.getImageUrl());
        Assertions.assertEquals(ResourceStatus.Pending, resource.getStatus());
    }

    @Test
    @Order(2)
    public void testUpdateResource() {
        Resource resource = resourceDAO.findByName("Resource 1");

        resource.setName("Resource 100");
        resource.setUrl("Resource 100 url");
        resource.setDescription("Resource 100 description");
        resource.setImageUrl("Resource 100 image url");
        resource.setUpdatedAt(new Date());
        resource.setStatus(ResourceStatus.Publish);

        resourceDAO.save(resource);

        Resource expectNullResource = resourceDAO.findByName("Resource 1");
        Assertions.assertNull(expectNullResource);

        Resource expectNotNullResource = resourceDAO.findByName("Resource 100");
        Assertions.assertNotNull(expectNotNullResource);

        Assertions.assertEquals("Resource 100", expectNotNullResource.getName());
        Assertions.assertEquals("Resource 100 url", expectNotNullResource.getUrl());
        Assertions.assertEquals("Resource 100 description", expectNotNullResource.getDescription());
        Assertions.assertEquals("Resource 100 image url", expectNotNullResource.getImageUrl());
        Assertions.assertEquals(ResourceStatus.Publish, expectNotNullResource.getStatus());
    }

    @Test
    @Order(3)
    public void testDeleteResource() {
        Resource resource = resourceDAO.findByName("Resource 100");
        Assertions.assertNotNull(resource);

        resourceDAO.delete(resource);

        Resource expectNullResource = resourceDAO.findByName("Resource 100");
        Assertions.assertNull(expectNullResource);
    }
}
