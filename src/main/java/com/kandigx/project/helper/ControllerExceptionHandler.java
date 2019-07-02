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
import javax.validation.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * 验证异常
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResultBean validatedMethodArgumentException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();

        Map<String, String> validResult = new HashMap<>();

        Map<String, Map<String, String>> listValid = new HashMap<>();
        String field;
        String indexKey;
        for (FieldError error : result.getFieldErrors()) {

            if ((field = error.getField()).contains("].")) {
                if (listValid.containsKey((indexKey = field.substring(0, field.indexOf("].") + 1)))) {
                    listValid.get(indexKey).put(field.substring(field.lastIndexOf("].") + 2), error.getDefaultMessage());
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(field.substring(field.lastIndexOf("].") + 2), error.getDefaultMessage());
                    listValid.put(indexKey, map);
                }
            } else {
                validResult.put(error.getField(), error.getDefaultMessage());
            }
        }

        if (!listValid.isEmpty()) {
            return ResultBean.validError(listValid.values().toArray());
        }

        return ResultBean.validError(validResult);
    }

    /**
     * 处理验证异常
     * @return
     */
//    @ExceptionHandler(ValidRequestException.class)
//    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
//    public ResultBean validRequestException(ValidRequestException e) {
//        return ResultBean.validError(e.getData());
//    }

}
