package com.earnestgem.andybook.web

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
class HelloControllerTest(
    @Autowired
    private val mvc: MockMvc // 웹 API를 테스트할 때 사용)
) {


    @Test
    fun helloTest() {
        val hello = "hello"

        mvc.perform(MockMvcRequestBuilders.get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청
            .andExpect(MockMvcResultMatchers.status().isOk) // mvc.perform 의 결과를 검증, HTTP STATUS 검증
            .andExpect(MockMvcResultMatchers.content().string(hello)) // 응답 본문의 내용을 검증
    }
}