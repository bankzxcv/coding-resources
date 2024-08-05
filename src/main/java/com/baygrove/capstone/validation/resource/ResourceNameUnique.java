package com.baygrove.capstone.validation.resource;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ResourceNameUniqueImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceNameUnique {
    String message() default "This resource name is already in use.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
