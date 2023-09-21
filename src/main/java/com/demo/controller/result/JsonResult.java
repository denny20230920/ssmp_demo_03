package com.demo.controller.result;

import lombok.Data;

@Data
public class JsonResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public JsonResult(Integer code, T data, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
