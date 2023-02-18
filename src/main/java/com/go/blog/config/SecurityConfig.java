package com.go.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//빈등록: 스프링 IOC 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration //빈등록 IOC로 관리
@EnableWebSecurity // 시큐리티 필터가 등록됨 = 스프링 시큐리티가 활성화가 도이ㅓ있는데 어떤 설정을 해당 파일에서 함 (securityConfig에서)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
             .antMatchers("/auth/**")
             .permitAll()
             .anyRequest()
             .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/loginForm"); //인증이 필요한 곳으로 요청이오면 자동으로 로그인 페이지 나오게 설정
    }
}
