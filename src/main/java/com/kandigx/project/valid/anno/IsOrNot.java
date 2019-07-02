package com.kandigx.project.valid.anno;

import com.kandigx.project.valid.validator.IsOrNotValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否
 *
 * @author kandigx
 * @create 2019-07-01 22:36
 */

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsOrNotValidator.class)
public @interface IsOrNot {

    String message() default "是否状态错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int[] value() default {0,1};

}
