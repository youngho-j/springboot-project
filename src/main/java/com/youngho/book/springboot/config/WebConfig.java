package com.youngho.book.springboot.config;

import com.youngho.book.springboot.config.auth.LoginUserArgumentResolver;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    /*
    *   HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의
    *   addArgumentResolvers 메소드를 통해 추가해야함
    * */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
