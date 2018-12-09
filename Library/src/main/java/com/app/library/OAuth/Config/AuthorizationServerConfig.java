package com.app.library.OAuth.Config;

import com.app.library.OAuth.CustomTokenStore.MongoClientDetailsService;
import com.app.library.OAuth.CustomTokenStore.MongoTokenStore;
import com.app.library.OAuth.CustomTokenStore.MongoUserDetailsService;
import com.app.library.OAuth.User.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import static com.app.library.OAuth.Constants.CLIENT_ID;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MongoUserDetailsService mongoUserDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(new MongoClientDetailsService())
                .withClient(CLIENT_ID)
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .secret("{noop}secret")
                .accessTokenValiditySeconds(120)//Access token is only valid for 2 minutes.
                .refreshTokenValiditySeconds(600);//Refresh token is only valid for 10 minutes.
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                 .tokenEnhancer(getTokenEnhancer())
                 .userDetailsService(mongoUserDetailsService)
                 .authenticationManager(authenticationManager);
    }

    private CustomTokenEnhancer getTokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    private TokenStore tokenStore() {
        return new MongoTokenStore();
    }

}