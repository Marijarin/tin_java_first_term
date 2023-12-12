package edu.hw_10.task1.annotations_util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = MinValueValidator.class)
public @interface MinValue {
    String message() default "Too small value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value();
}
