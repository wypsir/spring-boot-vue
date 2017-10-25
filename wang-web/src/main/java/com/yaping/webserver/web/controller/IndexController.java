package com.yaping.webserver.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yaping.common.entity.Picture;
import com.yaping.common.entity.Result;
import com.yaping.common.entity.User;
import com.yaping.common.entity.user.SysUser;
import com.yaping.common.exception.BaseException;
import com.yaping.common.exception.LoginException;
import com.yaping.common.support.login.LoginHelper;
import com.yaping.common.support.oauth.OAuthServiceDeractor;
import com.yaping.common.support.oauth.OAuthServices;
import com.yaping.common.util.ErrorUtil;
import com.yaping.common.util.IPUtil;
import com.yaping.common.util.Log;
import com.yaping.common.valid.Valid;
import com.yaping.webserver.web.service.IPictureService;
import com.yaping.webserver.web.service.ISysUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        model.addAttribute("oAuthServices", allOAuthServices);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@Validated(Valid.Login.class) User user, BindingResult result, Model model, HttpServletRequest request) {

        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            return Result.failure(fieldError);
        }
        try {
            if (LoginHelper.login(user.getNickname(), user.getPassword(), IPUtil.getClientIp(request))) {

                return Result.success();
            }
        } catch (Exception e) {
            if (e instanceof BaseException) {
                ((LoginException) e).handler(Result.failure());
            }
            return Result.serverInternalError();
        }
        return Result.failure();
    }

    @RequestMapping(value = {"/", "/index"})
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
            sysUser.setNickname(user.getNickname());
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

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        if (Objects.nonNull(subject) && subject.isAuthenticated()) {
            subject.logout();
        }
        return Result.success();
    }


    @Resource
    private ISysUserService userService;
    @Resource
    private IPictureService pictureService;


    @RequestMapping("/pictures")
    @ResponseBody
    public Object p(Page<Picture> page, DataTablesInput input,
                    @RequestParam(required = false, defaultValue = "0") int draw,
                    @RequestParam(required = false, defaultValue = "0") int start,
                    @RequestParam(required = false, defaultValue = "10") int length) {
        if (start > 0) {
            page.setCurrent((start - length) + 1);
        }
        page = pictureService.selectPage(page);
        DataTablesOutput<Picture> pictureDataTablesOutput = new DataTablesOutput<>();
        pictureDataTablesOutput.setDraw(draw);
        pictureDataTablesOutput.setRecordsTotal(Long.valueOf(page.getTotal()));
        pictureDataTablesOutput.setRecordsFiltered(Long.valueOf(page.getTotal()));
        pictureDataTablesOutput.setData(page.getRecords());
        HashMap<Object, Object> maps = new HashMap<>();
        maps.put("draw", draw);
        maps.put("recordsTotal", page.getTotal());
        maps.put("recordsFiltered", page.getTotal());
        maps.put("data", page.getRecords());
        return maps;
    }

    @RequestMapping("/pictures1")
    @ResponseBody
    public Result pictures1(Page<Picture> page, HttpServletRequest request) {

        Map<String, Object> condition = page.getCondition();
//        List<Picture>  pictures=  pictureService.selectUserList(page, condition);
//        page.setRecords(pictures);
        page = pictureService.selectPage(page, new EntityWrapper());
        EntityWrapper entityWrapper = new EntityWrapper();
        SqlHelper.fillWrapper(page,entityWrapper);
        List<Picture> list =  pictureService.selectUserList(page, entityWrapper);
        page.setRecords(list);
        return Result.page(page);
    }


}
