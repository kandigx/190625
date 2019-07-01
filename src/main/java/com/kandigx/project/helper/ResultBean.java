package com.kandigx.project.helper;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一响应结果
 *
 * @author kandigx
 * @create 2019-07-01 10:14
 */
@Getter
@Setter
public class ResultBean {

    /**
     * 信息编码
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;

    public ResultBean(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultBean(Object data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public static ResultBean success() {
        return new ResultBean(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
    }

    public static ResultBean badRequest() {
        return new ResultBean(ResultEnum.REQUEST_ERROR.getCode(),ResultEnum.REQUEST_ERROR.getMsg());
    }

    public static ResultBean validError(Object data) {
        return new ResultBean(ResultEnum.VALID_ERROR.getCode(),ResultEnum.VALID_ERROR.getMsg(),data);
    }

    public static ResultBean success(Object data) {
        return new ResultBean(data);
    }

    public static ResultBean unknownError(Object data) {
        return new ResultBean(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg(),data);
    }

}
