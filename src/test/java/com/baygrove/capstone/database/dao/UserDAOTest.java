package com.baygrove.capstone.database.dao;

import com.baygrove.capstone.database.entity.User;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    @Order(1)
    public void testCreateNewUser() {
        User user100 = new User();

        user100.setEmail("test100@test.com");
        user100.setPassword("1234");
        user100.setUsername("test100");
        user100.setCreatedAt(new Date());

        userDAO.save(user100);

        User user = userDAO.findByEmailIgnoreCase("test100@test.com");

        Assertions.assertNotNull(user);
        Assertions.assertEquals("test100@test.com", user.getEmail());
        Assertions.assertEquals("test100", user.getUsername());
    }


    @Order(2)
    @ParameterizedTest
    @ValueSource(strings = {"test100@test.com", "TEST100@test.com", "TEST100@TEST.COM", "tEsT100@TEst.cOm"})
    public void shouldFindEmail(String email) {
        Assertions.assertNotNull(userDAO.findByEmailIgnoreCase(email));
    }

    @Test
    @Order(3)
    public void testUpdateUser() {
        User user = userDAO.findByEmailIgnoreCase("test100@test.com");

        Assertions.assertNotNull(user);
        Assertions.assertEquals("test100", user.getUsername());
        Assertions.assertEquals("test100@test.com", user.getEmail());

        user.setUsername("test101");
        userDAO.save(user);

        User updatedUser = userDAO.findByEmailIgnoreCase("test100@test.com");

        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals("test101", updatedUser.getUsername());
    }


    @Test
    @Order(4)
    public void testDeleteUser() {
        User user = userDAO.findByEmailIgnoreCase("test100@test.com");

        Assertions.assertNotNull(user);
        Assertions.assertEquals("test100@test.com", user.getEmail());

        userDAO.delete(user);

        User expectedUser = userDAO.findByEmailIgnoreCase("test100@test.com");
        Assertions.assertNull(expectedUser);
    }
}
