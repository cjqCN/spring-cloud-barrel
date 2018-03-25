package com.cjq.springcloud.barrel.oauth.config;

import com.cjq.springcloud.barrel.oauth.converter.UserConvertable;
import com.cjq.springcloud.barrel.oauth.converter.UserConverter;
import com.cjq.springcloud.barrel.oauth.feign.FeignClientUpmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

/**
 * @author chenjinquan
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    FeignClientUpmsService feignClientUpmsService;

    @Bean
    @Override
    protected org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        org.springframework.security.core.userdetails.UserDetailsService userDetailsService = new CehnUserDetailsService(userConvertable(), feignClientUpmsService);
        return userDetailsService;
    }


    @Bean
    public UserConvertable userConvertable() {
        return new UserConverter();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService());
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
