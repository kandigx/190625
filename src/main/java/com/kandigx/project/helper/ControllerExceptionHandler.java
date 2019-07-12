package com.kandigx.project.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * controller 异常处理类
 *
 * @author kandigx
 * @create 2019-07-01 11:14
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 处理请求异常
     * @return
     */
    @ExceptionHandler({NoHandlerFoundException.class,})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultBean handle400Exception() {

        return ResultBean.badRequest();
    }

    /**
     * 验证 JSON 方式校验失败异常 ConstraintViolationException
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResultBean validatedException(ConstraintViolationException e) {

        Map<String, String> validResult = new HashMap<>();
        Map<String, Map<String, String>> listValid = new HashMap<>();
        String indexKey,propertyKey;
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            String path = violation.getPropertyPath().toString();

            if (path.contains("].")) {
                propertyKey = path.substring(path.lastIndexOf("].") + 2);
                if (listValid.containsKey((indexKey = path.substring(0, path.indexOf("].") + 1)))) {
                    listValid.get(indexKey).put(propertyKey, violation.getMessage());
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(propertyKey, violation.getMessage());
                    listValid.put(indexKey, map);
                }
            } else {
                validResult.put(path, violation.getMessage());
            }
        }
        if (!listValid.isEmpty()) {
            return ResultBean.validError(Arrays.asList(listValid.values().toArray()));
        }
        return ResultBean.validError(validResult);
    }

    /**
     * 验证表单方式校验失败异常 MethodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResultBean validatedMethodArgumentException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();

        Map<String, String> validResult = new HashMap<>();

        Map<String, Map<String, String>> listValid = new HashMap<>();
        String field,indexKey,propertyKey;

        for (FieldError error : result.getFieldErrors()) {

            if ((field = error.getField()).contains("].")) {
                propertyKey = field.substring(field.lastIndexOf("].") + 2);
                if (listValid.containsKey((indexKey = field.substring(0, field.indexOf("].") + 1)))) {
                    listValid.get(indexKey).put(propertyKey, error.getDefaultMessage());
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(propertyKey, error.getDefaultMessage());
                    listValid.put(indexKey, map);
                }
            } else {
                validResult.put(error.getField(), error.getDefaultMessage());
            }
        }

        if (!listValid.isEmpty()) {
            return ResultBean.validError(Arrays.asList(listValid.values().toArray()));
        }

        return ResultBean.validError(validResult);
    }


}
