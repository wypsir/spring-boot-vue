package com.yaping.common.listener;

import com.yaping.common.util.Log;
import com.yaping.common.util.WindowsServiceUtil;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Administrator on 2017/10/14.
 */
@WebListener
@Component
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Log.info("=============================");
        Log.info("System ContextInitialized ...");
        Log.info("=============================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Log.info("=============================");
        Log.info("System ContextDestroyed ...");
        Log.info("=============================");
    }
}
