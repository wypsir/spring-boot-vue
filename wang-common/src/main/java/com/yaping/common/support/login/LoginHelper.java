package com.yaping.common.support.login;

import com.yaping.common.exception.LoginException;
import com.yaping.common.util.ErrorUtil;
import com.yaping.common.util.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2017/10/15.
 */
public class LoginHelper {

    private LoginHelper() {
    }

    public static Boolean login(String account, String password, String host) {
//        LifecycleBeanPostProcessor bean = SpringUtils.getBean(LifecycleBeanPostProcessor.class);
//        if (Objects.nonNull(bean)) {
            UsernamePasswordToken token = new UsernamePasswordToken(account, password, host);
            token.setRememberMe(true);
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(token);
                return subject.isAuthenticated();
            } catch (UnknownAccountException uae) {
                throw new LoginException("用户名或密码不正确");
            } catch (IncorrectCredentialsException ice) {
                throw new LoginException("用户名或密码不正确");
            } catch (LockedAccountException lae) {
                throw new LoginException("用户名或密码不正确");
            } catch (ExcessiveAttemptsException eae) {
                throw new LoginException("用户名或密码错误次数过多");
            } catch (AuthenticationException ae) {
                //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
                Log.error("login AuthenticationException ex={}", ErrorUtil.writer(ae));
                throw new LoginException("用户名或密码不正确");
            }
//        }
//        return false;
    }

}
