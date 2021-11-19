package com.tistory.aristatait.config;

import com.tistory.aristatait.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-19
 * <p>
 * LoginUserArgumentResolver 가 스프링에서 인식될 수 있도록 WebMvcConfigurer 에 추가한다
 **/
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }
}
