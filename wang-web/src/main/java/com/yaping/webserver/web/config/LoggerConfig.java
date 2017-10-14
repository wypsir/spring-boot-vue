package com.yaping.webserver.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/19 11:37
 * @description
 */
@Configuration
public class LoggerConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        /**请求日志拦截器*/
//        registry.addInterceptor(new LoggerInterceptor());
    }

}
