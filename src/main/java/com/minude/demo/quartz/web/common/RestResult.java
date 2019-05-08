package com.minude.demo.quartz.web.common;

import lombok.Data;

/**
 * @Author minude
 * @Date 2019/3/28 10:19
 * @Version 1.0
 */
@Data
public class RestResult<T> {

    private int code;
    private String message;
    private T data;

    public RestResult(int code, String msg) {

        this.code = code;
        this.message = msg;
    }

    public static<T> RestResult<T> success() {

        return new RestResult<>(0, "success");
    }

    public static<T> RestResult<T> sysErr() {

        return new RestResult<>(10001, "system error");
    }

    public static<T> RestResult<T> success(T data) {

        RestResult<T> result = new RestResult<>(0, "success");
        result.setData(data);
        return result;
    }

}

