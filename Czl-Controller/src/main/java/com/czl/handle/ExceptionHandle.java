package com.czl.handle;

import com.czl.exception.ExpCodeEnum;
import com.czl.rsp.Result;
import com.czl.exception.CommonBizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author zerechen
 * REST接口的通用异常处理
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 业务异常处理
     * @param exception
     * @param <T>
     * @return
     */
    @ExceptionHandler(CommonBizException.class)
    public <T> Result<T> exceptionHandler(CommonBizException exception) {
        return Result.newFailureResult(exception);
    }

    /**
     * 请求方法不正确
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        throw new CommonBizException(ExpCodeEnum.HTTP_REQ_METHOD_ERROR);
    }

    /**
     * 系统异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public <T> Result<T> sysExpHandler(Exception exception) {
        logger.error("系统异常 ",exception);
        return Result.newFailureResult();
    }

}
