package com.wyp.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Administrator on 2017/10/11.
 */
@WebListener
@Component
public class SessionListener implements HttpSessionListener {
    private final Logger logger = LoggerFactory.getLogger(SessionListener.class);

    public static final String AllUSER_NUMBER = "SYSTEM:IPP:AllUSER_NUMBER";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        HttpSession session = se.getSession();
//        session.setAttribute();

        logger.info("创建了一个Session连接:[" + session.getId() + "]");
        redisTemplate.opsForSet().add(AllUSER_NUMBER,session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        if (getAllUserNumber() > 0) {
            logger.info("销毁了一个Session连接:[" + session.getId() + "]");
        }
        redisTemplate.opsForSet().remove(AllUSER_NUMBER, session.getId());
    }

    public Integer getAllUserNumber() {
        return redisTemplate.opsForSet().size(AllUSER_NUMBER).intValue();
    }
}
