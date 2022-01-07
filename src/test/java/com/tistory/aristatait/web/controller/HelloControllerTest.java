package com.tistory.aristatait.web.controller;

import com.tistory.aristatait.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 1.
 * 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행한다.
 * 여기서는 SpringRunner라는 스프링 실행자를 사용한다.
 * 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
 * <p>
 * 2.
 * WebMvcTest 는 WebSecurityConfigurerAdapter, WebMvcConfigurer, @ControllerAdvice, @Controller 를 읽는다.
 * 즉, @Repository, @Service, @Component 는 스캔 대상이 아니다.
 * SecurityConfig 를 스캔 대상에서 제외하는 이유는
 * SecurityConfig 를 생성하기 위해 필요한 CustomOAuth2UserService 가 스캔 대상이 아니어서
 * 에러가 발생하는 것을 방지하기 위함이다.
 */
@RunWith(SpringRunner.class) //1
@WebMvcTest(
        controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        } //2
)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입 받는다.
    private MockMvc mvc; // 웹 API 를 테스트 할 때 사용하는 클래스, 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.

    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc 를 통해 /hello 주소로 HTTP GET 요청을 한다.
                .andExpect(status().isOk()) // 위 행의 결과를 검증한다. Header 의 Status 가 200인지 아닌지 검증
                .andExpect(MockMvcResultMatchers.content().string(hello)); // Body 의 content 가 "hello" 인지 검증 한다.

    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
