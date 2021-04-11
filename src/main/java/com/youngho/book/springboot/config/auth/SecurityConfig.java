package com.youngho.book.springboot.config.auth;


import com.youngho.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


// @EnableWebSecurity - Spring Security 설정 활성화
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console화면을 사용하기 위해 해당 옵션 disable
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                // authorizeRequests - URL 별 권한 관리를 설정하는 옵션의 시작점 / 이게 선언 되어야 antMathers 사용가능
                // antMathers - 권한 관리 대상을 지정하는 옵션, URL, HTTP 메소드별로 관리 가능
                .authorizeRequests()
                // "/" 등이 지정된 URL은 permitALL 옵션을 사용해 전체 열람 권한 설정
                .antMatchers("/", "/css/**", "/images/**","/js/**","/h2-console/**").permitAll()
                // /api/v1/** 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 함
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // anyRequest - 설정된 값들 이외 나머지 URI를 나타냄, authenticated를 추가하여 인증된 사용자들(로그인한사람)에게만 허용
                .anyRequest().authenticated()
                .and()
                //.logout().logoutSuccessUrl("/") - 로그아웃 기능에 대한 여러 설정의 진입점, 로그아웃성공시 / 주소로 이동
                .logout().logoutSuccessUrl("/")
                .and()
                /*
                 *   .oauth2Login() - OAuth2 로그인 기능에 대한 여러 설정의 진입점
                 *   .userInfoEndpoint() - OAuth 로그인 성공 후 사용자 정보를 가져올 때 설정 담당
                 *   .userService - 소셜 로그인 성공시 이후 과정을 진행할 UserService 인터페이스의 구현체를 등록
                 *                  리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시 가능
                 * */
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);

    }

}
