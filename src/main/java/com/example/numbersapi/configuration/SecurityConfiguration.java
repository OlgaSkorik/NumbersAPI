package com.example.numbersapi.configuration;

import com.example.numbersapi.jwt.JWTConfig;
import com.example.numbersapi.jwt.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    private final JWTTokenProvider jwtTokenProvider;
//
//    private static final String ADMIN_ENDPOINT = "/api/admin/**";
//    private static final String LOGIN_ENDPOINT = "/api/auth/**";
//
//    @Autowired
//    public SecurityConfiguration(JWTTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(LOGIN_ENDPOINT).permitAll()
//                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
////                .antMatchers(HttpMethod.GET, PUBLIC_URLS).permitAll()
//                .antMatchers("/db/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .apply(new JWTConfig(jwtTokenProvider));
//        http
//                .headers().frameOptions().sameOrigin();
//    }
//}
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JWTTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String REGISTRATION_ENDPOINT = "/api/auth/registration";

    @Autowired
    public SecurityConfiguration(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(REGISTRATION_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JWTConfig(jwtTokenProvider));
        httpSecurity
                .headers().frameOptions().sameOrigin();
    }
}