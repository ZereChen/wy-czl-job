package com.czl.handle;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zerechen
 * REST接口统一的日志处理
 */
@ControllerAdvice
@ResponseBody
public class LogHandle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.czl.controller..*.*(..))")
    public void restLog(){}

    @Around("restLog()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        // 生成本次请求时间戳
        String timestamp = System.currentTimeMillis()+"";

        HttpServletRequest request = getHttpServletRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info(timestamp + ", url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);

        // 被拦截方法的返回值
        Object result = joinPoint.proceed();
        logger.info(timestamp + " , " + result.toString());
    }

    /**
     * 获取HttpServletRequest
     * @return
     */
    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }
}
