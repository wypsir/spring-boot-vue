package com.yaping.common.core;

/**
 * Created by Administrator on 2017/10/15.
 */
public interface BaseConstants {

    /** 当前用户 */
    String CURRENT_USER = "CURRENT_USER";
    /** 用户ip */
    String USER_IP = "USER_IP";
    /** 缓存命名空间 */
    String CACHE_NAMESPACE = ":Wang:";

    /** 在线用户数 */
    String ALLUSER_NUMBER = "SYSTEM:" + CACHE_NAMESPACE + "ALLUSER_NUMBER";
    /** token */
    String TOKEN = CACHE_NAMESPACE + "TOKEN";
}
