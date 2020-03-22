package com.gaoxi.annotation;

import java.lang.annotation.*;

/**
 * @author zerechen
 * @date 2017/10/27 下午10:28
 * @description 本注解用在Controller层的接口上，表示该接口是否需要登录
 */
// 本注解只能用在方法上
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {

    // 是否需要登录（默认为true）
    public boolean value() default true;

}
