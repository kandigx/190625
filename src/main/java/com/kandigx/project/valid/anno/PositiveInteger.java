package com.kandigx.project.valid.anno;

import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 正整数校验
 *
 * @author kandigx
 * @create 2019-07-02 13:59
 */
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Pattern(regexp = "^[1-9]\\d*$")
public @interface PositiveInteger {

    String message() default "只允许填写正整数";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
