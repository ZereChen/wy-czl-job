package com.czl.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.czl.entity.user.UserEntity;
import com.czl.exception.CommonBizException;
import com.czl.exception.ExpCodeEnum;
import com.czl.facade.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zerechen
 * @description 用户信息工具包
 */
@Component
public class UserUtil {

    @Value("${session.SessionIdName}")
    private String sessionIdName;

    @Autowired
    private RedisService redisService;

    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }

    /**
     * 从httpServletReq中获取用户信息UserEntity
     *
     * @param httpServletReq HTTP请求
     * @return 用户信息
     */
    public UserEntity getUser(HttpServletRequest httpServletReq) {
        // 获取SessionID
        String sessionID = getSessionID(httpServletReq);
        if (StringUtils.isEmpty(sessionID)) {
            return null;
        }

        // 获取UserEntity
        Object userEntity = redisService.get(sessionID);
        if (userEntity == null) {
            return null;
        }
        return (UserEntity) userEntity;

    }

    /**
     * 获取用户ID
     * @param httpReq HTTP请求
     * @return 用户ID
     */
    public String getUserId(HttpServletRequest httpReq) {
        UserEntity userEntity = getUser(httpReq);
        if (userEntity == null) {
            throw new CommonBizException(ExpCodeEnum.UNLOGIN);
        }

        return userEntity.getId();
    }
    /**
     * 从HttpServletRequest中获取SessionID
     *
     * @param request 当前的请求对象
     * @return SessionID的值
     */
    public String getSessionID(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        // 遍历所有cookie，找出SessionID
        String sessionID = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (sessionIdName.equals(cookie.getName())) {
                    sessionID = cookie.getValue();
                    break;
                }
            }
        }

        return sessionID;
    }
}
