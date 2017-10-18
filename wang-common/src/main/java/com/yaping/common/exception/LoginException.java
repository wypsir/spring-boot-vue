package com.yaping.common.exception;

import com.yaping.common.enums.ResultCode;

/**
 * Created by Administrator on 2017/10/15.
 */
public class LoginException extends BaseException  {

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    @Override
    protected ResultCode getResultEnum() {
        return ResultCode.FAIL;
    }
}
