package com.youngho.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;
    /*
    *   @After - Junit 단위 테스트가 끝날 떄마다 수행되는 메서드를 지정
    *            보통 배포 전 전체 테스트 수행시 테스트간 데이터 침범을 막기위해 사용
    * */
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }
    /*
    *   테스트 통과한것은 확인 했는데, 쿼리가 어떻게 돌아가는지 알고 싶을 경우에는 어떻게 해야하나?
    *   -> 스프링 부트의 application.properties, application.yml로 설정할 수 있도록 지원 권장
    *   
    *   사용 방법
    *   1. java/main/resources 에 application.properties 파일 생성
    *   2. spring.jpa.show-sql=true 코드 설정
    *   3. 다시 테스트 진행
    *
    *   결과
    *   Hibernate: ...(쿼리 내용)이 출력됨을 볼 수 있음
    *
    *   참고
    *   현재 H2 쿼리 문법이 적용중이기게 테이블 생성시 id가 bigint로 설정됨
    *   MySQL 쿼리 수행해도 정상 작동하기에 디버깅을 위해 출력되는 쿼리 로그를 MySQL로 변경
    *
    *   설정방법
    *   1. java/main/resources 에 application.properties 파일 생성
    *   2. spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect 코드 설정
    *   3. 다시 테스트 진행
    *
    *   결과
    *   Hibernate: create table posts (id bigint not null auto_increment, a.. 내용)dl 출력됨을 볼 수 있음
    *   
    * */
    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        /*
        *    postsRepository.save - posts 테이블에 insert/update 쿼리 실행
        *                           id 값이 있으면 update, 없으면 insert 쿼리 실행
        * */
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("alfkwl1239@gamil.com")
                .build());

        //when
        /*
        *   postsRepository.findAll() - posts 테이블에 있는 모든 데이터를 조회해오는 메서드
        * */
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}
