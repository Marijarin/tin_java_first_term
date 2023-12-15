package edu.hw_10.task1.annotations_util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinValueValidator implements ConstraintValidator<MinValue, Integer> {
    int minValue;

    @Override
    public void initialize(MinValue constraint) {
        this.minValue = constraint.value();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer > minValue;
    }
}
