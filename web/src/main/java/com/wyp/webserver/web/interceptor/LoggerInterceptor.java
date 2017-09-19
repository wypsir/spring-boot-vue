package com.wyp.webserver.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wyp.common.entity.HttpLog;
import com.wyp.common.util.IPUtil;
import com.wyp.common.util.SpringUtils;
import com.wyp.webserver.web.service.IBeautifulPicturesService;
import com.wyp.webserver.web.service.IHttpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/19 11:12 http请求日志拦截器
 * @description
 */
@Component
public class LoggerInterceptor implements HandlerInterceptor {


    public static final String LOGGER_SEND_TIME = "_send_time";
    public static final String LOGGER_ENTITY = "_http_log";


    @Autowired
    private IBeautifulPicturesService beautifulPicturesService;

    @Autowired
    private IHttpLogService logService;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpLog log = new HttpLog();
        String sessionId = httpServletRequest.getRequestedSessionId();
        String url = httpServletRequest.getRequestURI();
        String paramData = JSON.toJSONString(httpServletRequest.getParameterMap(), SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        String clientIp = IPUtil.getClientIp(httpServletRequest);

        log.setClientIp(clientIp);
        log.setMethod(httpServletRequest.getMethod());
        log.setType(httpServletRequest.getContentType());
        log.setParamData(paramData);
        log.setUri(url);
        log.setSessionId(sessionId);
        httpServletRequest.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());
        httpServletRequest.setAttribute(LOGGER_ENTITY, log);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        System.out.println(111111);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        HttpServletRequest request = httpServletRequest;
        HttpServletResponse response = httpServletResponse;

        int status = response.getStatus();
        long currentTime = System.currentTimeMillis();

        long time = Long.valueOf(httpServletRequest.getAttribute(LOGGER_SEND_TIME).toString());
        HttpLog log = (HttpLog) httpServletRequest.getAttribute(LOGGER_ENTITY);

        log.setTimeConsuming(Integer.valueOf((currentTime - time) + ""));

        log.setReturnTime(currentTime + "");
        log.setHttpStatusCode(status + "");
        if (logService == null) {
            logService = SpringUtils.getBean(IHttpLogService.class);
        }
        logService.insert(log);
    }

}
