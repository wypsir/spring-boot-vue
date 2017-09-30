package com.wyp.webserver.web.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wyp.common.entity.HttpLog;
import com.wyp.common.util.IPUtil;
import com.wyp.common.util.SpringUtils;
import com.wyp.webserver.web.service.IHttpLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/19 12:44
 * @description
 */
@Component
@Aspect
public class LogAspect {


    public static final String LOGGER_SEND_TIME = "_send_time";
    public static final String LOGGER_ENTITY = "_http_log";


    final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    final String springCglib = "$$EnhancerBySpringCGLIB$$";

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    IHttpLogService logService;

    private String PACKAGES_ENTITY_PREFIX = "com.lottery.gamble";

    @Pointcut("execution(* com.wyp.webserver.web.controller.*.*(..))")
    private void pcMethod() {
    }


    @Before(value = "pcMethod()")
    public void controllerBefore(JoinPoint point) {
        logger.info("controllerBefore ...");
        HttpLog log = new HttpLog();
        String sessionId = request.getRequestedSessionId();
        String url = request.getRequestURI();
        String paramData = JSON.toJSONString(request.getParameterMap(), SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        String clientIp = IPUtil.getClientIp(request);

        log.setClientIp(clientIp);
        log.setMethod(request.getMethod());
        log.setType(request.getContentType());
        log.setParamData(paramData);
        log.setUri(url);
        log.setSessionId(sessionId);
        request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());
        request.setAttribute(LOGGER_ENTITY, log);
    }

    @After(value = "pcMethod()")
    public void controllerAfter(JoinPoint point) {
        logger.info("controllerAfter ...");

    }

    @Around("pcMethod()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        logger.info("controllerAround ...");
        return  point.proceed();
    }

    @AfterReturning(pointcut = "pcMethod()",returning = "relVal")
    public void AfterReturning (JoinPoint point,Object relVal) {
        logger.info("AfterReturning ...");
        int status = response.getStatus();

        long time = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        HttpLog log = (HttpLog) request.getAttribute(LOGGER_ENTITY);
        log.setTimeConsuming(Integer.valueOf((currentTime - time) + ""));
        log.setReturnTime(currentTime + "");
        log.setHttpStatusCode(status + "");
        log.setReturnData(JSON.toJSONString(relVal, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue));
        if (logService == null) {
            logService = SpringUtils.getBean(IHttpLogService.class);
        }
        logService.insert(log);


    }


    @AfterThrowing(pointcut = "pcMethod()", throwing = "ex")
    public void afterThrowing(Throwable ex) throws Throwable {
        logger.info("afterThrowing ...");
    }
}
