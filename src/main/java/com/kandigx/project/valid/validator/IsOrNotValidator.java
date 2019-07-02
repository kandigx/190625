package com.kandigx.project.valid.validator;

import com.kandigx.project.valid.anno.IsOrNot;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * @author kandigx
 * @create 2019-07-01 22:38
 */
public class IsOrNotValidator implements ConstraintValidator<IsOrNot, Integer> {

    private Integer[] validStatus;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        List<Integer> status = Arrays.asList(validStatus);
        return status.contains(value);
    }
}
