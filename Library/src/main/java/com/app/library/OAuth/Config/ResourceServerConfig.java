package com.app.library.OAuth.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

import static com.app.library.OAuth.Constants.SIGN_UP_URL;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .and()
                    .authorizeRequests()
                        .antMatchers("/customers").access("hasRole('ROLE_ADMIN')")
                .and()
                    .authorizeRequests()
                        .anyRequest().authenticated()
                .and()
                    .exceptionHandling()
                        .accessDeniedHandler(((httpServletRequest, httpServletResponse, e) -> {
                            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN,"Access denied!! You don't have the privileges to access this resource." );
                        }))
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
                        });
    }

}
