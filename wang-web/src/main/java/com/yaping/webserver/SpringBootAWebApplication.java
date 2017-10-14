package com.yaping.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

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
