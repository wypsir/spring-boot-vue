package com.wyp.webserver.web.controller;

import com.wyp.common.entity.Result;
import com.wyp.common.entity.User;
import com.wyp.common.valid.Valid;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/18 11:20
 * @description
 */
@Controller
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


    @RequestMapping(value = "/")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@Validated(Valid.Login.class) User user, Model model) {
        if (user.getUsername().equals("admin") && user.getPassword().equals("admin")) {

            return "index";
        }
        model.addAttribute("message","用户名/密码错误！");
        return "login";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@Validated(Valid.Register.class) User user, BindingResult result,Model model) {

        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            model.addAttribute("message",fieldError.getDefaultMessage());
            return "register";
        }

        return "login";
    }

}
