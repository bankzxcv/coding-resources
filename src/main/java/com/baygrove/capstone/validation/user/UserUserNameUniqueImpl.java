package com.baygrove.capstone.validation.user;

import com.baygrove.capstone.database.dao.UserDAO;
import com.baygrove.capstone.database.entity.User;
import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.util.*;

@Slf4j
public class UserUserNameUniqueImpl implements ConstraintValidator<UserUserNameUnique, String> {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(username)) {
            return true;
        }

        User user = userDAO.findByUsernameIgnoreCase(username);

        return (user == null);
    }
}
