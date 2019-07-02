package com.kandigx.project.valid.validator;

import com.kandigx.project.valid.anno.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kandigx
 * @create 2019-07-02 14:10
 */
public class GenderValidator implements ConstraintValidator<Gender, String> {

   @Override
   public boolean isValid(String gender, ConstraintValidatorContext context) {

      return gender != null && gender.equals("1") || gender.equals("2");

   }

}
