package com.upload.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 林少君
 * @Date: 2020/6/5 15:12
 */
@Component
public class UploadInterceptor implements HandlerInterceptor {

    /**
     * preHandle是在请求执行前执行的
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    /**
     * postHandler是在请求结束之后,视图渲染之前执行的,但只有preHandle方法返回true的时候才会执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {}

    /**
     * afterCompletion是视图渲染完成之后才执行,同样需要preHandle返回true 该方法通常用于清理资源等工作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {}
}
