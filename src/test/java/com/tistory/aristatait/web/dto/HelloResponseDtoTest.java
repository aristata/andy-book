package com.tistory.aristatait.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int age = 10;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, age);

        //then
        Assertions.assertThat(dto.getName()).isEqualTo(name);
        Assertions.assertThat(dto.getAge()).isEqualTo(age);

    }
}
