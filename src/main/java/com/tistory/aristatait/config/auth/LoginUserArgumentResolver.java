package com.tistory.aristatait.config.auth;

import com.tistory.aristatait.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

/**
 * 최초 작성자 castlepeople
 * 최초 작성일 2021-11-19
 * <p>
 * HandlerMethodArgumentResolver 는 조건에 맞는 메소드가 있다면
 * 이 리졸버의 구현체가 지정한 값으로 해당 메소드의 파라미터로 넘길 수 있다
 **/
@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    /**
     * 컨트롤러 메소드의 특정 파라미터를 지원하는지 판단한다
     * 여기서는 파라미터에 @LoginUser 어노테이션이 붙어 있고, 파라미터 타입이 SessionUser 인 경우 true 를 반환
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    /**
     * 파라미터에 전달할 객체를 생성한다
     * 여기서는 세션에서 객체를 가져온다
     */
    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        return httpSession.getAttribute("user");
    }
}
