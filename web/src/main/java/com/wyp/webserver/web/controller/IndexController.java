package com.wyp.webserver.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyp.common.entity.BeautifulPictures;
import com.wyp.common.entity.Picture;
import com.wyp.common.entity.Result;
import com.wyp.common.entity.User;
import com.wyp.common.entity.user.SysUser;
import com.wyp.common.service.OAuthServiceDeractor;
import com.wyp.common.service.OAuthServices;
import com.wyp.common.util.ErrorUtil;
import com.wyp.common.util.Log;
import com.wyp.common.util.SpringUtils;
import com.wyp.common.valid.Valid;
import com.wyp.webserver.web.service.IBeautifulPicturesService;
import com.wyp.webserver.web.service.IPictureService;
import com.wyp.webserver.web.service.ISysUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/18 11:20
 * @description
 */
@Controller
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

    @Autowired
    private OAuthServices oAuthServices;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(Model model) {
        List<OAuthServiceDeractor> allOAuthServices = oAuthServices.getAllOAuthServices();
        model.addAttribute("oAuthServices",allOAuthServices);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Validated(Valid.Login.class) User user, Model model) {
        try {
            Object shiroConfig = null;
            try {
                shiroConfig = SpringUtils.getBean("shiroConfig");
            } catch (BeansException e) {
            }

            if (Objects.nonNull(shiroConfig)) {
                UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
                SecurityUtils.getSubject().login(token);
            } else {

            }

            return "index";
        } catch (AuthenticationException e) {
            Log.error("login error:{}", ErrorUtil.writer(e));
        }
        model.addAttribute("message", "用户名/密码错误！");
        return "login";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String toRegister() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Validated(Valid.Register.class) User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            model.addAttribute("message", fieldError.getDefaultMessage());
            return "register";
        }
        boolean insert = false;
        try {
            SysUser sysUser = new SysUser();
            sysUser.setNickname(user.getUsername());
            sysUser.setEmail(user.getEmail());
            String password = new SimpleHash("md5", user.getPassword(), null, 2).toHex();

            sysUser.setPswd(password);
            sysUser.setStatus(0L);
            sysUser.setCreateTime(new Date());
            insert = userService.insert(sysUser);

        } catch (Exception e) {
            Log.error("register error:{}", ErrorUtil.writer(e));
            model.addAttribute("message", "注册失败");
        }
        if (!insert) {
            model.addAttribute("message", "注册失败");
            return "register";
        }

        return "login";
    }

    @RequestMapping(value = "/test")
    public void test() {
        int i = 0 / 0;

    }


    @Resource
    private ISysUserService userService;
    @Resource
    private IPictureService pictureService;


    @RequestMapping("/pictures")
    @ResponseBody
    public Page p(Page<Picture> page) {
        page = pictureService.selectPage(page);

        return page;
    }


}
