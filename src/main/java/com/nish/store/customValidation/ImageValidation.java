package com.nish.store.customValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidation.class)
public @interface ImageValidation {
    // For error message
    String message() default "Invalid Image Name";
    // Represent group of constraints
    Class<?>[] groups() default { };
    // Additional information about annotaion
    Class<? extends Payload>[] payload() default { };
}
