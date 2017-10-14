package com.yaping.common.annotation.limit;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/10/2.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
    /**
     * 访问次数
     * @return
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 时间间隔，毫秒值
     * @return
     */
    long time() default 60000;

}
