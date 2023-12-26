package edu.hw_10.task1.annotations_util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotNullValidator.class)
public @interface NotNull {
    String message() default "Must not be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

