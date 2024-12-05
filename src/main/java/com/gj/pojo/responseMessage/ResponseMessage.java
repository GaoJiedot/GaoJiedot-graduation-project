package com.gj.pojo.responseMessage;

import com.gj.pojo.Order;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;
    private String status;
    private long totalElements;
    private int totalPages;

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

    public static ResponseMessage uploadsuccess(String message, String url) {
        return new ResponseMessage(HttpStatus.OK.value(), "success", url);
    }

    public static ResponseMessage success(Page<?> page) {
        // 使用带参构造函数创建 ResponseMessage 对象
        ResponseMessage response = new ResponseMessage<>(HttpStatus.OK.value(), "success", page.getContent());

        // 填充分页信息
        response.status = "success";
        response.totalElements = page.getTotalElements(); // 获取总记录数
        response.totalPages = page.getTotalPages(); // 获取总页数

        return response;
    }



}
