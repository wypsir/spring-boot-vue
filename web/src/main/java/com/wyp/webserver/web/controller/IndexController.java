package com.wyp.webserver.web.controller;

import com.wyp.common.entity.Result;
import com.wyp.common.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/18 11:20
 * @description
 */
@RestController
@RequestMapping("/api/")
public class IndexController {


    @ApiOperation(value = "登录", notes = "用户名密码登录")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping("login")
    public Result login(@RequestParam String username, @RequestParam String password) {

        return Result.success((Object) (username + password));
    }


    @ApiOperation(value = "登录2", notes = "用户名密码登录")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "login2")
    public Result login2(User user) {

        return Result.success(user);
    }

    @ApiOperation(value = "登录3", notes = "用户名密码登录")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "login3", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result login3(@RequestBody User user) {

        return Result.success(user);
    }


}
