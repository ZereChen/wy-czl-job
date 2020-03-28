package com.czl.order.annotation;

import com.czl.order.processor.Processor;

import java.lang.annotation.*;

/**
 * @author zerechen
 * @date 2017/11/9 下午2:43
 *
 * @description 向ProcessEngine中注入所有的Processor
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectProcessors {
    public Class<? extends Processor>[] value();
}