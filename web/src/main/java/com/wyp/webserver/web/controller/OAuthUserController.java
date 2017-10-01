package com.wyp.webserver.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wyp.common.entity.oauth.OAuthUser;
import com.wyp.common.entity.user.SysUser;
import com.wyp.common.service.OAuthServiceDeractor;
import com.wyp.common.service.OAuthServices;
import com.wyp.webserver.web.service.IOAuthUserService;
import com.wyp.webserver.web.service.ISysUserService;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wyp
 * @since 2017-10-01
 */
@Controller
@RequestMapping("/oauth")
public class OAuthUserController {

    @Autowired
    private OAuthServices oAuthServices;
    @Autowired
    private IOAuthUserService authUserService;

    @Autowired
    private ISysUserService userService;

    @RequestMapping(value = "/{type}/callback", method = RequestMethod.GET)
    public String callback(@RequestParam(value = "code") String code, @PathVariable(value = "type") String type,
                           HttpServletRequest request, Model model) {
        OAuthServiceDeractor oAuthService = oAuthServices.getOAuthService(type);
        Token accessToken = oAuthService.getAccessToken(null, new Verifier(code));
        OAuthUser oAuthInfo = oAuthService.getOAuthUser(accessToken);
        Wrapper<OAuthUser> where = new EntityWrapper<OAuthUser>().where("oAuth_type={0}", oAuthInfo.getoAuthType()).and("oAuth_id=" + oAuthInfo.getoAuthId());
        OAuthUser oAuthUser = authUserService.selectOne(where);
        if (oAuthUser == null) {
            model.addAttribute("oAuthInfo", oAuthInfo);
            return "register";
        }
        request.getSession().setAttribute("oauthUser", oAuthUser);

        return "redirect:index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, SysUser user,
                           @RequestParam(value = "oAuthType", required = false, defaultValue = "") String oAuthType,
                           @RequestParam(value = "oAuthId", required = true, defaultValue = "") String oAuthId,
                           HttpServletRequest request) {
        OAuthUser oAuthInfo = new OAuthUser();
        oAuthInfo.setoAuthId(oAuthId);
        oAuthInfo.setoAuthType(oAuthType);
        Wrapper<SysUser> where = new EntityWrapper<SysUser>().where("nickname={0}", user.getNickname());

        if (userService.selectOne(where) != null) {
            model.addAttribute("errorMessage", "用户名已存在");
            model.addAttribute("oAuthInfo", oAuthInfo);
            return "register";
        }
        userService.insert(user);
        Wrapper<OAuthUser> where1 = new EntityWrapper<OAuthUser>().where("oAuth_type={0}", oAuthInfo.getoAuthType()).and("oAuth_id=" + oAuthInfo.getoAuthId());
        OAuthUser oAuthUser = authUserService.selectOne(where1);
        if (oAuthUser == null) {
            oAuthInfo.setUser(user);
            authUserService.insert(oAuthInfo);
        }
        request.getSession().setAttribute("oauthUser", oAuthUser);
        return "redirect:/index";
    }

}
