package com.wyp.common.exception;

/**
 * Created by Administrator on 2017/10/2.
 * http请求异常类
 */
public class RequestLimitException extends Exception {

    public RequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }
}
