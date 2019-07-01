package com.kandigx.project.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

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
     * 处理验证异常
     * @return
     */
    @ExceptionHandler(ValidRequestException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResultBean validRequestException(ValidRequestException e) {
        return ResultBean.validError(e.getData());
    }

}
