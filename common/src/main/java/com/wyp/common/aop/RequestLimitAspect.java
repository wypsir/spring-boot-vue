package com.wyp.common.aop;

import com.wyp.common.annotation.limit.RequestLimit;
import com.wyp.common.exception.RequestLimitException;
import com.wyp.common.util.ErrorUtil;
import com.wyp.common.util.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2017/10/2.
 *
 * @requestLimit 注解拦截器
 */
@Aspect
@Component
public class RequestLimitAspect {

    public static String req_profix = "req_limit_";

    private Map<String, Integer> reqCaches = new HashMap<>();

    @Autowired
    private HttpServletRequest request;

    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
    public void requestLimit(JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException {

        try {
            String ip = request.getLocalAddr();

            String url = request.getRequestURL().toString();

            String key = req_profix.concat(url).concat(ip);

            Integer value = reqCaches.get(key);
            if (Objects.isNull(value) || value == 0) {
                reqCaches.put(key, 1);
            } else {
                reqCaches.put(key, reqCaches.get(value + 1));
            }

            int count = reqCaches.get(key);

            if (count > 0) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        reqCaches.remove(key);
                    }
                };
                timer.schedule(task, limit.time());
            }
            if (count > limit.count()) {
                Log.warn("IP[{}]访问[{}]超过了限定的次数[{}", ip, url, limit.count());
                throw new RequestLimitException("");
            }
        } catch (RequestLimitException e) {
            throw e;
        } catch (Exception e) {
            Log.error("RequestLimitAspect.requestLimit() error|ex={}", ErrorUtil.writer(e));
        }

    }


}
