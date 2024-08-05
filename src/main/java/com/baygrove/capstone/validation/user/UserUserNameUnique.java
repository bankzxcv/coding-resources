package com.baygrove.capstone.validation.user;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserUserNameUniqueImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserUserNameUnique {
    String message() default "{UserUsernameUnique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}