package com.yaping.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017/10/11.
 */
@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {

//    @Bean
//    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
//        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean();
//        listenerRegistrationBean.setListener(new ServerListener());
//        return listenerRegistrationBean;
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ws").setViewName("/ws");
    }
}
