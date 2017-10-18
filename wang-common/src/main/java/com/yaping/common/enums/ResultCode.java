package com.yaping.common.enums;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/18 13:33
 * @description
 */
public enum ResultCode {
    OK(200, "成功"),
    BAD_REQUEST(400, "请求参数错误"),
    FAIL(404, "失败"),
    ERROR(20000, "服务异常!"),
    SERVER_INTERNAL_ERROR(500, "服务器内部错误！"),;
    private int code;
    private String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getErrorDesc(int code) {
        for (ResultCode rc : ResultCode.values()) {
            if (rc.getCode() == code) {
                return rc.getDesc();
            }
        }
        return "";
    }
}
