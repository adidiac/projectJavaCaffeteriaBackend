package com.cafeteria.cafeteria.security;

import com.cafeteria.cafeteria.Aspects.TokenValidationAspect;
import com.cafeteria.cafeteria.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    public TokenValidationAspect tokenValidationAspect() {
        return new TokenValidationAspect(jwtTokenUtil);
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(jwtTokenUtil);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests((authz) -> authz.anyRequest().authenticated());
        return http.build();
    }

}
