package com.yaping.webserver.web.config;

import com.yaping.common.util.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @date 2017/10/1 14:41
 * @description
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Value("${cbs.imagesPath}")
    private String imagePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        if (imagePath.equals("") || imagePath.equals("${cbs.imagesPath}")) {
            String imagesPath = WebAppConfig.class.getClassLoader().getResource("").getPath();
            if (imagesPath.indexOf(".jar") > 0) {
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            } else if (imagesPath.indexOf("classes") > 0) {
                imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/images/";
            imagePath = imagesPath;
        }
        Log.info("imagePath:{}", imagePath);
        registry.addResourceHandler("/images/**").addResourceLocations(imagePath);
        super.addResourceHandlers(registry);
    }


    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }
}
