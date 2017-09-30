package com.wyp.common.annotation;

import com.wyp.common.validator.PhoneNoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { PhoneNoValidator.class})
public @interface PhoneNo {

    //默认错误消息
    String message() default "手机号码错误";

    //分组
    Class<?>[] groups() default { };

    //负载
    Class<? extends Payload>[] payload() default { };

    //指定多个时使用
    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        PhoneNo[] value();
    }
}