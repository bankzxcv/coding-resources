package com.baygrove.capstone.validation;


import com.baygrove.capstone.database.dao.UserDAO;
import com.baygrove.capstone.database.entity.User;
import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.util.*;


@Slf4j
public class UserEmailUniqueImpl implements ConstraintValidator<UserEmailUnique, String> {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        User user = userDAO.findByEmailIgnoreCase(value);

        return (user == null);
    }
}
