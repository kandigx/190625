package com.kandigx.project.valid.anno;

import com.kandigx.project.valid.validator.GenderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 性别
 * 男-1，女-2
 * @author kandigx
 * @create 2019-07-02 14:09
 */
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
public @interface Gender {

    String message() default "性别状态码错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
