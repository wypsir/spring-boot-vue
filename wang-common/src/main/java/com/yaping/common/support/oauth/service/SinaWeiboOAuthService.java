package com.yaping.common.support.oauth.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.yaping.common.support.oauth.OAuthUser;
import com.yaping.common.entity.user.SysUser;
import com.yaping.common.enums.OAuthTypes;
import com.yaping.common.support.oauth.OAuthServiceDeractor;
import com.yaping.common.util.Log;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * Created by Administrator on 2017/10/15.
 * 新浪微博认证服务
 */
public class SinaWeiboOAuthService extends OAuthServiceDeractor {

    private static final String PROTECTED_RESOURCE_URL = "https://api.weibo.com/oauth2/get_token_info";

    public SinaWeiboOAuthService(OAuthService oAuthService, String type) {
        super(oAuthService, OAuthTypes.SAIN_WEI_BO);
    }

    @Override
    public OAuthUser getOAuthUser(Token accessToken) {
        OAuthRequest request = new OAuthRequest(Verb.GET,PROTECTED_RESOURCE_URL);
        this.signRequest(accessToken,request);
        Response response = request.send();
        OAuthUser oAuthUser = new OAuthUser();
        oAuthUser.setoAuthType(getoAuthType());
        Object result = JSON.parse(response.getBody());
        Log.info("result={}",response.getBody());
        oAuthUser.setoAuthId(JSONPath.eval(result,"$.uid").toString());
        oAuthUser.setUser(new SysUser());
        return oAuthUser;
    }
}
