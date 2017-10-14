package com.yaping.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;

/**
 * Created by Administrator on 2017/10/11.
 */
@WebListener
@Component
public class ServerListener implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger logger = LoggerFactory.getLogger(ServerListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("------------------------------------");
        logger.info("系统【{}】启动完成！", event.getSpringApplication().getSources().iterator().next());
        logger.info("------------------------------------");
    }
}
