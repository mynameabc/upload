package com.project;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 林少君
 * @Date: 2020/5/28 12:22
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -4149726986568498605L;

    /**
     * 请求成功返回码为：0000
     */
    private static final String successCode = "0000";

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回描述
     */
    private String msg;

    public Response(){
        this.msg = "";
        this.code = successCode;
    }

    public Response(String code, String msg){
        this();
        this.code = code;
        this.msg = msg;
    }

    public Response(String code, String msg, Object data){
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> getSUCCESS() {
        Response<T> response = new Response<T>();
        response.setCode("0000");
        response.setMsg("请求成功!");
        return response;
    }

    public static <T> Response<T> getSUCCESS(T data) {
        Response<T> response = new Response<T>();
        response.setCode("0000");
        response.setMsg("请求成功!");
        response.setData(data);
        return response;
    }

    public static <T> Response<T> getSUCCESS(String msg) {
        Response<T> response = new Response<T>();
        response.setCode(ResponseCode.EXECUTE_FAIL.getCode());
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> getSUCCESS(String msg, Object data) {
        Response<T> response = new Response<T>();
        response.setCode(ResponseCode.EXECUTE_SUCCESS.getCode());
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> getFAIL(ResponseCode responseCode) {
        Response<T> response = new Response<T>();
        response.setCode(responseCode.getCode());
        response.setMsg(responseCode.getMsg());
        return response;
    }

    public static <T> Response<T> getFAIL(String msg) {
        Response<T> response = new Response<T>();
        response.setCode(ResponseCode.EXECUTE_FAIL.getCode());
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> getFAIL(String msg, Object data) {
        Response<T> response = new Response<T>();
        response.setCode(ResponseCode.EXECUTE_FAIL.getCode());
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> getFAIL(ResponseCode responseCode, Object data) {
        Response<T> response = new Response<T>();
        response.setCode(responseCode.getCode());
        response.setMsg(responseCode.getMsg());
        response.setData(data);
        return response;
    }

    @Override
    public String toString() {
        return "Response{" +
                ", code='" + code + '\'' +
                ", msg=" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
