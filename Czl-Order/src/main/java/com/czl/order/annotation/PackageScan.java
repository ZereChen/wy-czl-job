package com.czl.order.annotation;

import java.lang.annotation.*;

/**
 * @author zerechen
 * @date 2017/11/7 下午4:42
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PackageScan {
    public String value();
}
