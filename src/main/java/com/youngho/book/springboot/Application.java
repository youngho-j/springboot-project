package com.youngho.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


// @SpringBootApplication - 스프링 부트의 자동 설정, 스프링 Bean 읽기 생성 모두 자동설정
// @SpringBootApplication 선언 위치부터 설정을 읽어감 따라서, 프로젝트 최상단에 위치해야함
// @EnableJpaAuditing - JPA Auditing 어노테이션을 모두 활성화 할 수 있도록 함
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    // 프로젝트의 메인 클래스
    public static void main(String[] args) {
        // 내장 WAS 실행 - 스프링 부트는 내장 WAS 실행 권장 왜? 언제든지 같은 환경에서 배포가능해서
        SpringApplication.run(Application.class, args);
    }
}
