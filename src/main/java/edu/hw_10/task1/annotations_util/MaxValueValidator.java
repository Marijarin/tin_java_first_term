package edu.hw_10.task1.annotations_util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MaxValueValidator implements ConstraintValidator<MaxValue, Integer> {
    int maxValue;

    @Override
    public void initialize(MaxValue constraint) {
        this.maxValue = constraint.value();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer > 0 && integer < maxValue;
    }
}
