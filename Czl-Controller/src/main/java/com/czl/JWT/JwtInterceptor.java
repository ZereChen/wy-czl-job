package com.czl.JWT;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.czl.annotation.NoLogin;
import com.czl.entity.user.AccessAuthEntity;
import com.czl.entity.user.PermissionEntity;
import com.czl.entity.user.UserEntity;
import com.czl.exception.CommonBizException;
import com.czl.exception.ExpCodeEnum;
import com.czl.facade.redis.RedisService;
import com.czl.utils.AnnotationUtil;
import com.czl.utils.RedisPrefixUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;


/**
 * token验证拦截器
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Audience audience;
    private JwtTokenUtil jwtTokenUtil;
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NoLogin jwtIgnore = AnnotationUtil.getAnnotationValueByMethod(handlerMethod.getMethod(), NoLogin.class);
            if (jwtIgnore != null) {
                return true;
            }
        }
        //放行OPTIONS请求
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        if(audience == null || jwtTokenUtil == null || redisService == null){
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            audience = (Audience) factory.getBean("audience");
            jwtTokenUtil = (JwtTokenUtil) factory.getBean("jwtTokenUtil");
            redisService = (RedisService) factory.getBean("redisServiceImpl");
        }

        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        jwtTokenUtil.parseJWT(request, audience.getBase64Secret());
        // 获取用户信息
        UserEntity userEntity = jwtTokenUtil.getUserEntity(request,audience);

        // 获取本次接口权限信息
        AccessAuthEntity accessAuthEntity = getAccessAuthEntity(request.getMethod(), request.getServletPath());

        // 检查是否拥有权限
        checkPermission(userEntity, accessAuthEntity);
        return true;
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