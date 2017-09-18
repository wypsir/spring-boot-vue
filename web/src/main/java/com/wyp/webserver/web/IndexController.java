package com.wyp.webserver.web;

import com.wyp.common.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/18 11:20
 * @description
 */
@RestController
@RequestMapping("/api/")
public class IndexController {


    @RequestMapping("login")
    public Result login(String username, String password) {

        return Result.success((Object) (username + password));
    }


}
