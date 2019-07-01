package com.kandigx.project.helper;

import lombok.Getter;

/**
 * 响应信息枚举类
 *
 * @author kandigx
 * @create 2019-07-01 10:18
 */
@Getter
public enum ResultEnum {

    /**
     * 成功
     */
    SUCCESS("0001","成功"),


    /**
     * 请求地址错误
     */
    REQUEST_ERROR("9001","请求地址错误"),

    /**
     * 验证错误
     */
    VALID_ERROR("9002","验证错误"),

    /**
     * 未知错误
     */
    UNKNOWN_ERROR("9999","未知错误");

    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultEnum fromCode(String code) {
        for (ResultEnum result : ResultEnum.values()) {
            if (result.getCode().equals(code)) {
                return result;
            }
        }
        return null;
    }

}
