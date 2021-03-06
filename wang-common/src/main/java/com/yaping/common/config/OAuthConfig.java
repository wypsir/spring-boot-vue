package com.yaping.common.config;

import com.yaping.common.support.oauth.api.GithubApi;
import com.yaping.common.enums.OAuthTypes;
import com.yaping.common.support.oauth.service.GithubOAuthService;
import com.yaping.common.support.oauth.OAuthServiceDeractor;
import org.scribe.builder.ServiceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date 2017/10/1 17:28
 * @description
 */
@Configuration
public class OAuthConfig {
    private static final String CALLBACK_URL = "%s/oauth/%s/callback";

    @Value("${oAuth.github.state}")
    String state;
    @Value("${oAuth.github.clientId}")
    String githubClientId;
    @Value("${oAuth.github.clientSecret}")
    String githubClientSecret;
    @Value("${demo.host}")
    String host;

    @Bean
    public GithubApi githubApi() {
        return new GithubApi(state);
    }

    @Bean
    public OAuthServiceDeractor getGithubOAuthService() {
        return new GithubOAuthService(new ServiceBuilder()
                .provider(githubApi())
                .apiKey(githubClientId)
                .apiSecret(githubClientSecret)
                .callback(String.format(CALLBACK_URL, host, OAuthTypes.GITHUB))
                .build());
    }

}
