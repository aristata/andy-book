package com.earnestgem.andybook.web.dto

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HelloResponseDtoTest {

    @Test
    fun test_dataClass() {
        val name = "test"
        val amount = 1000

        val dto = HelloResponseDto(name, amount)

        Assertions.assertThat(dto.name).isEqualTo(name)
        Assertions.assertThat(dto.amount).isEqualTo(amount)
    }
}