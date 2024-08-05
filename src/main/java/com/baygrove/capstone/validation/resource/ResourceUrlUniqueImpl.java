package com.baygrove.capstone.validation.resource;

import com.baygrove.capstone.database.dao.ResourceDAO;
import com.baygrove.capstone.database.entity.Resource;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class ResourceUrlUniqueImpl implements ConstraintValidator<ResourceUrlUnique, String> {

    @Autowired
    private ResourceDAO resourceDAO;

    @Override
    public boolean isValid(String resourceUrl, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(resourceUrl)) {
            return true;
        }

        Resource resource = resourceDAO.findByUrl(resourceUrl);

        return (resource == null);
    }
}
