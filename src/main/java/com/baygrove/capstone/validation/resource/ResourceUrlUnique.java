package com.baygrove.capstone.validation.resource;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ResourceUrlUniqueImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceUrlUnique {
    String message() default "This resource url is already in use.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
