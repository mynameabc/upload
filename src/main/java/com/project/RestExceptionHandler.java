package com.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author: 林少君
 * @Date: 2020/5/28 12:24
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Response unknownException(Exception e) {
        log.error("未知异常:{}", getException(e));
        return Response.getFAIL(ResponseCode.UnknownException);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Response runtimeExceptionHandler(RuntimeException e) {
        log.error("运行异常:{}", getException(e));
        return Response.getFAIL(ResponseCode.RuntimeException);
    }

    @ExceptionHandler(NullPointerException.class)
    public Response nullPointerExceptionHandler(NullPointerException e) {
        log.error("空指针异常:{}", getException(e));
        return Response.getFAIL(ResponseCode.NullPointerException);
    }

    @ExceptionHandler(ClassCastException.class)
    public Response classCastExceptionHandler(ClassCastException e) {
        log.error("类型转换异常:{}", getException(e));
        return Response.getFAIL(ResponseCode.ClassCastException);
    }

    @ExceptionHandler(IOException.class)
    public Response IOExceptionHandler(IOException e) {
        log.error("IO异常:{}", getException(e));
        return Response.getFAIL(ResponseCode.IOException);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Response indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
        log.error("数组越界异常:{}", getException(e));
        return Response.getFAIL(ResponseCode.IndexOutOfBoundsException);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public Response requestTypeMismatch(MethodArgumentTypeMismatchException e) {
        log.error("参数类型不匹配异常:{}", getException(e));
        return Response.getFAIL(ResponseCode.MethodArgumentTypeMismatchException);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Response httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("控制器方法中@RequestBody类型参数数据类型转换异常:{}", getException(e));
        return Response.getFAIL(ResponseCode.HttpMessageNotReadableException);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("控制器方法参数异常:{}", getException(e));
        return Response.getFAIL(ResponseCode.MethodArgumentNotValidException);
    }

    private String getException(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter,true));
        return stringWriter.getBuffer().toString();
    }
}
