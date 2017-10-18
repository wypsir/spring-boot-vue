package com.yaping.common.support.oauth;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * @date 2017/10/1 17:18
 * @description
 */
public abstract class OAuthServiceDeractor implements OAuthService{

    private OAuthService oAuthService;
    private String oAuthType;
    private String authorizationUrl;


    public OAuthServiceDeractor(OAuthService oAuthService, String type) {
        super();
        this.oAuthService = oAuthService;
        this.oAuthType = type;
        this.authorizationUrl = oAuthService.getAuthorizationUrl(null);
    }

    @Override
    public Token getRequestToken() {
        return oAuthService.getRequestToken();
    }

    @Override
    public Token getAccessToken(Token token, Verifier verifier) {
        return oAuthService.getAccessToken(token,verifier);
    }

    @Override
    public void signRequest(Token token, OAuthRequest oAuthRequest) {
        oAuthService.signRequest(token,oAuthRequest);
    }

    @Override
    public String getVersion() {
        return oAuthService.getVersion();
    }

    @Override
    public String getAuthorizationUrl(Token requestToken) {
        return oAuthService.getAuthorizationUrl(requestToken);
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public String getoAuthType() {
        return oAuthType;
    }

    public abstract OAuthUser getOAuthUser(Token accessToken);
}
