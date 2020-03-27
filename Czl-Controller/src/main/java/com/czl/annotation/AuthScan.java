package com.czl.annotation;

import java.lang.annotation.*;

/**
 * @author zerechen
 * @description 指定需要扫描 用户鉴权 所在的包
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthScan {
    public String value();
}
