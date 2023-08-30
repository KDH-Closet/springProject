package com.chunjae.kdhcloset.util;

import com.chunjae.kdhcloset.member.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final MemberDetailsService memberDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrfConfig) ->
                        csrfConfig.disable()
                )
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()
                        )
                )
                .authorizeHttpRequests((authorizationRequest) ->
                        authorizationRequest
                                .requestMatchers("/**").permitAll()
                )
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/")
                                .usernameParameter("member_id")
                                .passwordParameter("password")
                                .loginProcessingUrl("/login-proc")
                                .defaultSuccessUrl("/shop/top", true)
                )
                .logout((logoutConfig) ->
                        logoutConfig.logoutSuccessUrl("/"))
                .rememberMe((rememberMe) ->
                        rememberMe
                                .rememberMeParameter("remember-me")
                                .tokenValiditySeconds(3600)
                                .alwaysRemember(false)
                                .userDetailsService(memberDetailsService));


        return http.build();
    }
}
