package com.youngho.book.springboot;

import com.youngho.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @RunWith - 테스트 진행시 JUnit에 내장된 실행자 외 다른 실행자 실행 즉, 스프링 부트 테스트와 JUnit의 연결자 역할
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    // @Autowired - 스프링이 관리하는 bean 주입 받음
    // MockMvc - 웹 API를 테스트시 사용(HTTP, GET, POST 등), 스프링 MVC 테스트의 시작점
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        /* mvc.perform(get("/hello")) - MockMvc를 통해 HTTP GET 요청
        *  .andExpect(status().isOk()) - mvc.perform의 결과 검증 즉, HTTP Header의 Status를 검증 여기서는 200인지 아닌지 검사
        *  .andExpect(content().string(hello)) - mvc.perform의 결과 검증 즉, 응답 본분의 내용 검증 여기서는 hello 값이 맞는지 검사
        * */
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));

    }
}
