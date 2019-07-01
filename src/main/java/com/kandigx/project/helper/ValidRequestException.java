package com.kandigx.project.helper;

import lombok.Getter;
import lombok.Setter;

/**
 * 验证异常类
 *
 * @author kandigx
 * @create 2019-07-01 11:21
 */
@Setter
@Getter
public class ValidRequestException extends Exception {

    private Object data;

    public ValidRequestException(Object data) {
        super("验证信息错误异常");
        this.data = data;
    }
}
