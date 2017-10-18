package com.yaping.webserver;

import com.yaping.shiro.config.ShiroConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(value = "com.yaping"
//        ,excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ShiroConfig.class)}
)
@SpringBootApplication
@ServletComponentScan
public class SpringBootAWebApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootAWebApplication.class, args);
    }

}
