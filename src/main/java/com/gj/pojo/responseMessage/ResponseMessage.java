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
    public static <T> ResponseMessage<T> success() {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success", null);
    }
    public static ResponseMessage success(List<Order> data) {
        // 创建并返回包含列表的响应消息
        return new ResponseMessage(HttpStatus.OK.value(), "success", data);
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
