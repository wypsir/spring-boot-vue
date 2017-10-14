package com.yaping.webserver.web.controller.restApi;

import com.yaping.common.entity.Result;
import com.yaping.common.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/18 13:47
 * @description
 */
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list() {
        return Result.success(users);
    }

    @ApiOperation(value = "创建用户", notes = "根据user对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result createUser(@Validated User user) throws Exception {
//        if (result.hasErrors()) {
//            return Result.failure();
//        }
        users.put(user.getId(), user);
        return Result.success();
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getUser(@PathVariable Long id) {

        return Result.success(users.get(id));
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setUsername(user.getUsername());
        u.setAge(user.getAge());
        users.put(id, u);
        return Result.success(users);
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable Long id) {
        users.remove(id);
        return Result.success();
    }




}
