package com.gj.pojo;

import org.springframework.http.HttpStatus;

public class ResponseMessage <T>{
    private Integer code;
    private String message;
    private T data;
    public ResponseMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public <T>ResponseMessage<T> success(T data) {
        return new ResponseMessage<T>(HttpStatus.OK.value(), "success", data);
    }
    public <T>ResponseMessage<T> success() {
        return new ResponseMessage<T>(HttpStatus.OK.value(), "success", null);
    }
}
