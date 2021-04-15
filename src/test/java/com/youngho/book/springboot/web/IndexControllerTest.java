package com.youngho.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩() {
        //when
        String body = this.restTemplate.getForObject("/",String.class);

        //then - URL 호출시 페이지의 내용이 제대로 호출되는지 확인(전체코드를 다 검증할 필요는 없음)
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스 Ver.2");
        /*
        *   테스트 시 오류 발생 - 원인 : body부분에 해당 스프링 부트로 시작하는 웹 서비스 Ver.2 해당 내용이
        *   존재하지 않아서 발생, 초기에 스프링 부트로 시작하는 웹 서비스로 테스트를 수행하고 결과를 확인 했으나
        *   이후  index 수정당시 메인페이지 h1 태그 이름을 변경했는데 테스트 코드에는 적용을 하지 않아서 발생
        * */
    }
}
