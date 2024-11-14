package com.gj.Exception;


import com.gj.pojo.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GloblExceptionHandlerAdvice {
    Logger log = LoggerFactory.getLogger(GloblExceptionHandlerAdvice.class);
    @ExceptionHandler
    public ResponseMessage handleException(Exception e, HttpServletRequest request, HttpServletResponse response){
        log.error("统一异常",e);
        return new ResponseMessage(500,"error",null);
    }
}
