package com.yaping.common.entity;

import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yaping.common.enums.ResultCode;

import java.io.Serializable;

public class Result<T> implements Serializable {


    private static final long serialVersionUID = -7284167079213189980L;
    private int code;
    private String msg;

    private static final String OK = "操作成功";
    private static final String ERROR = "操作失败";
    private static final int SUCCESS_CODE = 200;
    private static final int ERROR_CODE = 400;

    private T data;

    private int count;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public Result() {
    }

    public Result(T t) {
        this.code = 200;
        this.data = t;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg, T data, int count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    /**
     * 对web，manage 处理
     */

    public static Result success() {

        return new Result(SUCCESS_CODE, OK);
    }

    public static <W> Result<W> success(W data) {
        if (data instanceof Page) {
            Page page = ((Page) data);
            return new Result<W>(SUCCESS_CODE, OK, (W) page.getRecords(), page.getTotal());
        }
        return new Result<W>(SUCCESS_CODE, OK, data);
    }

    public static <W> Result<W> success(ResultCode resultCode, W data) {
        if (resultCode == null) {
            return Result.success(data);
        }
        return new Result<W>(resultCode.getCode(), resultCode.getDesc(), data);
    }

    public static <W> Result<W> success(String message, W data) {
        return new Result<W>(SUCCESS_CODE, message, data);
    }

    public static Result success(String message) {
        return new Result(SUCCESS_CODE, message);
    }

    public static Result failure(int code) {
        return new Result(code, ResultCode.getErrorDesc(code));
    }

    public static Result failure() {
        return new Result(ERROR_CODE, ERROR);
    }

    public static Result failure(String message) {
        return new Result(ERROR_CODE, message);
    }

    public static <W> Result<W> failure(W data) {
        return new Result<W>(ERROR_CODE, ERROR, data);
    }


    public static <W> Result<W> failure(String message, W data) {
        return new Result<W>(ERROR_CODE, message, data);
    }


    public static Result failure(int code, String message) {
        return new Result(code, message);
    }

    public static Result error() {
        return new Result(ResultCode.ERROR.getCode(), ResultCode.ERROR.getDesc());
    }

    public static Result serverInternalError() {
        return new Result(ResultCode.SERVER_INTERNAL_ERROR.getCode(), ResultCode.SERVER_INTERNAL_ERROR.getDesc());
    }

    public static Result error(int code, String message) {
        return new Result(code, message);
    }


    public static Result failure(ResultCode result) {
        if (result == null) {
            return error();
        }
        return new Result(result.getCode(), result.getDesc());
    }

    @JsonIgnore
    public boolean isOk() {
        return SUCCESS_CODE == this.getCode();
    }

    @JsonIgnore
    public boolean isNotOk() {
        return !isOk();
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static Result page(Page page) {
        int total = page.getTotal();
        if (total > 0) {
            return success(page);
        }
        return Result.failure();
    }
}