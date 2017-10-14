package com.yaping.common.annotation;

import com.yaping.common.validator.CheckPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/18 15:27
 * @description 密码一致性校验
 */

@Target({ TYPE,FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
//指定验证器
@Constraint(validatedBy = CheckPasswordValidator.class)
@Documented
public @interface CheckPassword {

    //默认错误消息
    String message() default "密码不一致";

    //分组
    Class<?>[] groups() default { };

    //负载
    Class<? extends Payload>[] payload() default { };

    //指定多个时使用
    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CheckPassword[] value();
    }
}
