package com.yaping.webserver.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yaping.common.entity.oauth.OAuthUser;
import com.yaping.common.entity.user.SysUser;
import com.yaping.common.service.OAuthServiceDeractor;
import com.yaping.common.service.OAuthServices;
import com.yaping.webserver.web.service.IOAuthUserService;
import com.yaping.webserver.web.service.ISysUserService;
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
    public String callback(@RequestParam(value = "code",required = false) String code, @PathVariable(value = "type") String type,
                           HttpServletRequest request, Model model) {
        OAuthServiceDeractor oAuthService = oAuthServices.getOAuthService(type);
        Token accessToken = oAuthService.getAccessToken(null, new Verifier(code));
        OAuthUser oAuthInfo = oAuthService.getOAuthUser(accessToken);
        Wrapper<OAuthUser> where = new EntityWrapper<OAuthUser>().where("oAuth_type={0}", oAuthInfo.getoAuthType()).and("oAuth_id=" + oAuthInfo.getoAuthId());
        OAuthUser oAuthUser = authUserService.selectOne(where);
        if (oAuthUser == null) {
            model.addAttribute("oAuthInfo", oAuthInfo);
            model.addAttribute("oauth", 0);
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
        String username = request.getParameter("username");
        Wrapper<SysUser> where = new EntityWrapper<SysUser>().where("nickname={0}", username);

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
