package com.hzgy.interceptor.valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基类
 */
abstract class BaseInterceptor {

    @Resource
    public ResourceBundleMessageSource resourceBundleMessageSource;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<>();
    private ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<>();

    void preProcess() {
        requestLocal.set(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        responseLocal.set(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse());
    }

    HttpServletRequest getRequest() {
        return requestLocal.get();
    }

    HttpServletResponse getResponse() {
        return responseLocal.get();
    }
}

