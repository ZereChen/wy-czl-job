package com.czl.handle;

import com.czl.util.UserUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@ResponseBody
@Aspect
@Component
public class LogHandle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.czl.controller..*.*(..))")
    public void restLog(){}

    @Autowired
    private UserUtil userUtil;
    @Around("restLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 生成本次请求时间戳
        String timestamp = System.currentTimeMillis()+"";

        HttpServletRequest request = userUtil.getHttpServletRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info(timestamp + ", url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);

        // 被拦截方法的返回值
        Object result = joinPoint.proceed();
        logger.info(timestamp + " , " + result.toString());
        return result;
    }


}
