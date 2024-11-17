package com.gj.pojo.responseMessage;

import com.gj.pojo.Order;
import org.springframework.http.HttpStatus;

import java.util.List;


public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success", data);
    }

    public static <T> ResponseMessage<T> success(String message) {
        return new ResponseMessage<>(HttpStatus.OK.value(), message, null);
    }

    public static <T> ResponseMessage<T> success(List<Order> data) {
        return new ResponseMessage(HttpStatus.OK.value(), "success", data);
    }

    public static <T> ResponseMessage<T> error(String message) {
        return new ResponseMessage<>(500, message, null);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
