package com.baygrove.capstone.validation.resource;

import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.entity.Resource;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class ResourceNameUniqueImpl implements ConstraintValidator<ResourceNameUnique, String> {
    @Autowired
    private ResourceDAO resourceDAO;

    @Override
    public boolean isValid(String resourceName, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(resourceName)) {
            return true;
        }

        Resource resource = resourceDAO.findByName(resourceName);

        return (resource == null);
    }
}
