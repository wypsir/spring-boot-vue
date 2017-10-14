package com.yaping.shiro.auth;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.yaping.common.entity.user.SysUser;
import com.yaping.common.util.Log;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @date 2017/10/1 9:36
 * @description
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    IService<SysUser> userInfoService;



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Log.info("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        Wrapper<SysUser> where = new EntityWrapper<SysUser>().where("nickname={0}", username);
        SysUser userInfo = userInfoService.selectOne(where);
        Log.info("----->>userInfo={}", JSON.toJSONString(userInfo));
        if (userInfo == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo.getNickname(), //用户名
                userInfo.getPswd(), //密码
//                ByteSource.Util.bytes(username + userInfo.getCreateTime()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
