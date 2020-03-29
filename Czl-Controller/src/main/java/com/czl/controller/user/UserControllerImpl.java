package com.czl.controller.user;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.czl.entity.user.*;
import com.czl.enumeration.KeyGeneratorPrefixEnum;
import com.czl.exception.CommonBizException;
import com.czl.exception.ExpCodeEnum;
import com.czl.facade.redis.RedisService;
import com.czl.facade.user.UserService;
import com.czl.req.BatchReq;
import com.czl.req.user.*;
import com.czl.rsp.Result;
import com.czl.util.UserUtil;
import com.czl.utils.KeyGenerator;
import com.czl.utils.MD5Util;
import com.czl.utils.RedisPrefixUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.czl.rsp.Result.newFailureResult;
import static com.czl.rsp.Result.newSuccessResult;


@RestController
public class UserControllerImpl implements UserController {

    @Reference(version = "1.0.0",timeout = 100000)
    private UserService userService;

    @Autowired
    private RedisService redisService;

    /** Session有效时间 */
    @Value("${session.expireTime}")
    private long sessionExpireTime;

    @Value("${session.SessionIdName}")
    private String sessionIdName;

    @Autowired
    private UserUtil userUtil;



    @Override
    public Result login(LoginReq loginReq, HttpServletResponse httpRsp) {
        // 校验参数
        if (StringUtils.isEmpty(loginReq.getUsername())) {
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.USERNAME_NULL));
        }
        if (StringUtils.isEmpty(loginReq.getPassword())) {
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.PASSWD_NULL));
        }
        String inputPassword = loginReq.getPassword();
        loginReq.setPassword(null);

        // 登录鉴权
        UserEntity userEntity = userService.login(loginReq);
        // 登录失败
        if (userEntity == null || !inputPassword.equals(MD5Util.encode(userEntity.getPassword()))){
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.LOGIN_FAIL));
        }
        // 登录成功
        doLoginSuccess(userEntity, httpRsp);
        return Result.newSuccessResult(userEntity);
    }

    @Override
    public Result logout(HttpServletRequest httpReq, HttpServletResponse httpRsp) {
        String sessionID = userUtil.getSessionID(httpReq);

        // 将 SessionID-UserEntity 从Redis中移除
        redisService.remove(sessionID);

        // 将SessionID从HTTP响应头中删除
        Cookie cookie = new Cookie(sessionIdName, null);
        httpRsp.addCookie(cookie);

        // 登出成功
        return Result.newSuccessResult();
    }

    @Override
    public Result isLogin(HttpServletRequest request) {
        UserEntity userEntity = userUtil.getUser(request);

        if (userEntity==null) {
            return newFailureResult();
        }
        return newSuccessResult(userEntity);
    }

    /**
     * 处理登录成功
     * @param userEntity 用户信息
     * @param httpRsp HTTP响应参数
     */
    private void doLoginSuccess(UserEntity userEntity, HttpServletResponse httpRsp) {
        //抹出password
        userEntity.setPassword(null);
        // 生成SessionID
        String sessionID = KeyGenerator.getKey(KeyGeneratorPrefixEnum.SESSION_ID_PREFIX);

        // 将 SessionID-UserEntity 存入Redis
        redisService.set(sessionID, userEntity, sessionExpireTime);

        // 将SessionID存入HTTP响应头
        Cookie cookie = new Cookie(sessionIdName, sessionID);
        httpRsp.addCookie(cookie);
    }


}
