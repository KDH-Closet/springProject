package com.chunjae.kdhcloset.util;

import com.chunjae.kdhcloset.member.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
                                .defaultSuccessUrl("/shop", true)
                )
                .logout((logoutConfig) ->
                        logoutConfig
                                .logoutUrl("/mypage/logout")
                                .logoutSuccessUrl("/"))
                .rememberMe((rememberMe) ->
                        rememberMe
                                .rememberMeParameter("remember-me")
                                .tokenValiditySeconds(3600)
                                .alwaysRemember(false)
                                .userDetailsService(memberDetailsService));


        return http.build();
    }
}
