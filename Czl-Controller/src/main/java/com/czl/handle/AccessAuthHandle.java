package com.czl.handle;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.czl.entity.user.PermissionEntity;
import com.czl.entity.user.UserEntity;
import com.czl.exception.CommonBizException;
import com.czl.exception.ExpCodeEnum;
import com.czl.facade.redis.RedisService;
import com.czl.entity.user.AccessAuthEntity;
import com.czl.util.UserUtil;
import com.czl.utils.RedisPrefixUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author zerechen
 * @description 访问权限处理类(所有请求都要经过此类)
 */
@Aspect
@Component
public class AccessAuthHandle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserUtil userUtil;


    @Pointcut("execution(public * com.czl.controller..*.*(..))")
    public void accessAuth() {
    }


    /**
     * 拦截所有请求
     */
//    @Before("accessAuth()")
//    public void doBefore() {
//        authentication();
//    }

    /**
     * 访问鉴权：检查当前用户是否允许访问该接口
     */
    private void authentication() {
        // 获取 HttpServletRequest
        HttpServletRequest request = userUtil.getHttpServletRequest();
        String method = request.getMethod();
        String url = request.getServletPath();

        // 获取用户信息UserEntity
        UserEntity userEntity = userUtil.getUser(request);

        // 获取接口权限信息
        AccessAuthEntity accessAuthEntity = getAccessAuthEntity(method, url);

        // 检查权限
        authentication(userEntity, accessAuthEntity);
    }

    /**
     * 检查权限
     * @param userEntity       当前用户的信息
     * @param accessAuthEntity 当前接口的访问权限
     */
    private void authentication(UserEntity userEntity, AccessAuthEntity accessAuthEntity) {
        // 无需登录
        if (!accessAuthEntity.isLogin()) {
            return;
        }

        // 检查是否登录
        checkLogin(userEntity, accessAuthEntity);

        // 检查是否拥有权限
        checkPermission(userEntity, accessAuthEntity);
    }

    /**
     * 检查当前用户是否拥有访问该接口的权限
     *
     * @param userEntity       用户信息
     * @param accessAuthEntity 接口权限信息
     */
    private void checkPermission(UserEntity userEntity, AccessAuthEntity accessAuthEntity) {
        // 获取接口权限
        String accessPermission = accessAuthEntity.getPermission();

        // 获取用户权限
        List<PermissionEntity> userPermissionList = userEntity.getRoleEntity().getPermissionList();

        // 判断用户是否包含接口权限
        if (CollectionUtils.isNotEmpty(userPermissionList)) {
            for (PermissionEntity permissionEntity : userPermissionList) {
                if (permissionEntity.getPermission().equals(accessPermission)) {
                    return;
                }
            }
        }

        // 没有权限
        throw new CommonBizException(ExpCodeEnum.NO_PERMISSION);
    }


    /**
     * 检查当前接口是否需要登录
     *
     * @param userEntity       用户信息
     * @param accessAuthEntity 接口访问权限
     */
    private void checkLogin(UserEntity userEntity, AccessAuthEntity accessAuthEntity) {
        // 尚未登录
        if (accessAuthEntity.isLogin() && userEntity == null) {
            throw new CommonBizException(ExpCodeEnum.UNLOGIN);
        }
    }

    /**
     * 获取接口的权限信息
     *
     * @param method 请求方式
     * @param url    请求路径
     * @return 该接口对应的权限信息
     */
    private AccessAuthEntity getAccessAuthEntity(String method, String url) {
        // 获取所有接口的访问权限
        Map<String, AccessAuthEntity> accessAuthMap = redisService.getMap(RedisPrefixUtil.Access_Auth_Prefix);

        // 遍历所有接口的访问权限
        if (accessAuthMap != null && accessAuthMap.size() > 0) {
            for (String key : accessAuthMap.keySet()) {
                if (isMatch(key, method, url)) {
                    return accessAuthMap.get(key);
                }
            }
        }

        // 没有该接口
        throw new CommonBizException(ExpCodeEnum.ERROR_404);
    }


    /**
     * 判断key是否与method+url匹配
     *
     * @param key    接口的 "method + url"
     * @param method
     * @param url
     * @return
     */
    private boolean isMatch(String key, String method, String url) {
        // 清除首尾的反斜杠
        if (url.startsWith("/")) {
            url = url.substring(1);
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        // 将URL按照反斜杠切分
        String[] urls_1 = key.split("/");
        String[] urls_2 = url.split("/");
        urls_2[0] = method + urls_2[0];

        // 反斜杠数量不同，则匹配失败
        if (urls_1.length != urls_2.length) {
            return false;
        }

        // 逐个匹配
        for (int i = 0; i < urls_1.length; i++) {
            // 若当前是个* 或 当前字符串相等，则匹配下一个
            if ("*".equals(urls_1[i]) || urls_1[i].equals(urls_2[i])) {
                continue;
            }

            // 若两个字符串不相等，则匹配失败
            return false;
        }

        // 匹配成功
        return true;
    }
}
