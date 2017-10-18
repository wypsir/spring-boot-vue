package com.yaping.common.exception;

import com.yaping.common.entity.Result;
import com.yaping.common.enums.ResultCode;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2017/10/15.
 */
public abstract class BaseException extends RuntimeException {


    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public void handler(Result result) {
        result.setCode(getResultEnum().getCode());
        if (!StringUtils.isNotBlank(getMessage())) {
            result.setMsg(getMessage());
        } else {
            result.setMsg(getResultEnum().getDesc());
        }
    }

    protected abstract ResultCode getResultEnum();
}
