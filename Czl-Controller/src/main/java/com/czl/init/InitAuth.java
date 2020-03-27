package com.czl.init;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.czl.annotation.AuthScan;
import com.czl.annotation.Login;
import com.czl.annotation.Permission;
import com.czl.entity.user.AccessAuthEntity;
import com.czl.enumeration.HttpMethodEnum;
import com.czl.facade.redis.RedisService;
import com.czl.utils.AnnotationUtil;
import com.czl.utils.ClassUtil;
import com.czl.utils.RedisPrefixUtil;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author zerechen
 * @description 初始化用户信息
 */
@AuthScan("com.czl.controller")
@Component
public class InitAuth implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;

    /** 接口权限列表 */
    private Map<String,AccessAuthEntity> accessAuthMap = Maps.newHashMap();



    @Override
    public void run(String... strings) throws Exception {
        loadAccessAuth();
    }

    /**
     * 加载接口访问权限
     */
    private void loadAccessAuth() throws IOException {
        // 获取需要 用户鉴权 的包名
        AuthScan authScan = AnnotationUtil.getAnnotationValueByClass(this.getClass(), AuthScan.class);
        String pkgName = authScan.value();

        // 获取包下所有类
        List<Class<?>> classes = ClassUtil.getClasses(pkgName);
        if (CollectionUtils.isEmpty(classes)) {
            return;
        }

        // 遍历所有类
        for (Class clazz : classes) {
            Method[] methods = clazz.getMethods();
            if (methods==null || methods.length==0) {
                continue;
            }

            // 遍历类中到函数
            for (Method method : methods) {
                AccessAuthEntity accessAuthEntity = buildAccessAuthEntity(method);
                if (accessAuthEntity!=null) {
                    // 生成key
                    String key = generateKey(accessAuthEntity);
                    // 存至本地Map
                    accessAuthMap.put(key, accessAuthEntity);
                    logger.debug("",accessAuthEntity);
                }
            }
        }
        // 存至Redis
        redisService.setMap(RedisPrefixUtil.Access_Auth_Prefix, accessAuthMap, null);
        logger.info("loadAccessAuth() completed！"+accessAuthMap);
    }

    /**
     * 生成接口权限信息的Key
     * Key = 请求方式+请求URL
     * @param accessAuthEntity 接口权限信息
     * @return Key
     */
    private String generateKey(AccessAuthEntity accessAuthEntity) {
        return accessAuthEntity.getHttpMethodEnum().getMsg() +
                accessAuthEntity.getUrl();
    }

    /**
     * 由方法 构造AccessAuthEntity对象
     * @param method
     * @return
     */
    private AccessAuthEntity buildAccessAuthEntity(Method method) {
        GetMapping getMapping = AnnotationUtil.getAnnotationValueByMethod(method, GetMapping.class);
        PostMapping postMapping = AnnotationUtil.getAnnotationValueByMethod(method, PostMapping.class);
        PutMapping putMapping= AnnotationUtil.getAnnotationValueByMethod(method, PutMapping.class);
        DeleteMapping deleteMapping = AnnotationUtil.getAnnotationValueByMethod(method, DeleteMapping.class);

        AccessAuthEntity accessAuthEntity = null;
        if (getMapping!=null
                && getMapping.value()!=null
                && getMapping.value().length==1
                && StringUtils.isNotEmpty(getMapping.value()[0])) {
            accessAuthEntity = new AccessAuthEntity();
            accessAuthEntity.setHttpMethodEnum(HttpMethodEnum.GET);
            accessAuthEntity.setUrl(trimUrl(getMapping.value()[0]));
        } else if (postMapping!=null
                && postMapping.value()!=null
                && postMapping.value().length==1
                && StringUtils.isNotEmpty(postMapping.value()[0])) {
            accessAuthEntity = new AccessAuthEntity();
            accessAuthEntity.setHttpMethodEnum(HttpMethodEnum.POST);
            accessAuthEntity.setUrl(trimUrl(postMapping.value()[0]));
        } else if (putMapping!=null
                && putMapping.value()!=null
                && putMapping.value().length==1
                && StringUtils.isNotEmpty(putMapping.value()[0])) {
            accessAuthEntity = new AccessAuthEntity();
            accessAuthEntity.setHttpMethodEnum(HttpMethodEnum.PUT);
            accessAuthEntity.setUrl(trimUrl(putMapping.value()[0]));
        } else if (deleteMapping!=null
                && deleteMapping.value()!=null
                && deleteMapping.value().length==1
                && StringUtils.isNotEmpty(deleteMapping.value()[0])) {
            accessAuthEntity = new AccessAuthEntity();
            accessAuthEntity.setHttpMethodEnum(HttpMethodEnum.DELETE);
            accessAuthEntity.setUrl(trimUrl(deleteMapping.value()[0]));
        }

        // 解析@Login 和 @Permission
        if (accessAuthEntity!=null) {
            accessAuthEntity = getLoginAndPermission(method, accessAuthEntity);
            accessAuthEntity.setMethodName(method.getName());
        }

        return accessAuthEntity;
    }


    /**
     * 处理URL
     * 1. 将URL两侧的斜杠去掉
     * 2. 将URL中的"{xxx}"替换为"*"
     * @param url 原始URL
     * @return 处理后的URL
     */
    private static String trimUrl(String url) {
        // 清除首尾的反斜杠
        if (url.startsWith("/")) {
            url = url.substring(1);
        }
        if (url.endsWith("/")) {
            url = url.substring(0,url.length()-1);
        }

        // 将"{xxx}"替换为"*"
        // TODO 正则表达式要继续完善（纠正/user/{xxxxx}/{yyyy}——>user/*的情况）
        url = url.replaceAll("\\{(.*)\\}","*");
        return url;
    }

    /**
     * 获取指定方法上的@Login的值和@Role的值
     * @param method 目标方法
     * @param accessAuthEntity
     * @return
     */
    private AccessAuthEntity getLoginAndPermission(Method method, AccessAuthEntity accessAuthEntity) {
        // 获取@Permission的值
        Permission permission = AnnotationUtil.getAnnotationValueByMethod(method, Permission.class);
        if (permission!=null && StringUtils.isNotEmpty(permission.value())) {
            accessAuthEntity.setPermission(permission.value());
            accessAuthEntity.setLogin(true);
            return accessAuthEntity;
        }

        // 获取@Login的值
        Login login = AnnotationUtil.getAnnotationValueByMethod(method, Login.class);
        if (login!=null) {
            accessAuthEntity.setLogin(true);
        }

        accessAuthEntity.setLogin(false);
        return accessAuthEntity;
    }

    public static void main(String[] args) {
        System.out.println("user:"+trimUrl("user"));
        System.out.println("{}:"+trimUrl("{}"));
        System.out.println("/user:"+trimUrl("/user"));
        System.out.println("/user/:"+trimUrl("/user/"));
        System.out.println("user/{xxxx}:"+trimUrl("user/{xxxx}"));
        System.out.println("/user/{xxxxx}/{yyyy}:"+trimUrl("/user/{xxxxx}/{yyyy}"));
        System.out.println("/user/home/{sdsds}:"+trimUrl("/user/home/{sdsds}"));
        System.out.println("/user/{home}/{zzzzz}/:"+trimUrl("/user/{home}/{zzzzz}/"));
    }

}
