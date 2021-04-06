package com.youngho.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponeseDto dto = new HelloResponeseDto(name, amount);

        //then
        // assertThat - assertj 라이브러리의 검증 메서드 / 메서드 체이닝 지원(메서드를 이어붙여서 사용가능함)
        // isEqualTo - assertj 라이브러리의 동등 비교 메서드
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
