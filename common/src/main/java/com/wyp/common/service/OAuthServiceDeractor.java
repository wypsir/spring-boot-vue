package com.wyp.common.service;

import com.wyp.common.entity.oauth.OAuthUser;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * @date 2017/10/1 17:18
 * @description
 */
public abstract class OAuthServiceDeractor implements OAuthService{

    private final OAuthService oAuthService;
    private final String oAuthType;
    private final String authorizationUrl;


    public OAuthServiceDeractor(OAuthService oAuthService, String type) {
        super();
        this.oAuthService = oAuthService;
        this.oAuthType = type;
        this.authorizationUrl = oAuthService.getAuthorizationUrl(null);
    }

    @Override
    public Token getRequestToken() {
        return null;
    }

    @Override
    public Token getAccessToken(Token token, Verifier verifier) {
        return null;
    }

    @Override
    public void signRequest(Token token, OAuthRequest oAuthRequest) {

    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public String getAuthorizationUrl(Token token) {
        return authorizationUrl;
    }

    public String getoAuthType() {
        return oAuthType;
    }

    public abstract OAuthUser getOAuthUser(Token accessToken);
}
