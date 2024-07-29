package com.baygrove.capstone.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserEmailUniqueImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserEmailUnique {
    String message() default "{UserEmailUnique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}