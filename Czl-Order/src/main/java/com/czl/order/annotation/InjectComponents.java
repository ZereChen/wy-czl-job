package com.czl.order.annotation;

import com.czl.order.component.BaseComponent;

import java.lang.annotation.*;

/**
 * 向List注入Component的注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectComponents {
    public Class<? extends BaseComponent>[] value();
}
