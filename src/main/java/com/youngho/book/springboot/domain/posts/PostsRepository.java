package com.youngho.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import  org.springframework.data.jpa.repository.Query;

import java.util.List;
/*
*   보통 MyBatis나 ibatis에서는 Dao라고 불리는 DB Layer 접근자
*   JPA에서는 Repository라고 부르며 인터페이스로 생성
*   꼭 인터페이스 생성 후 JpaRepository<Entity 클래스, PK타입> 상속 필요, 이과정을 마치면 기본적인 CRUD 메소드 자동 생성
*
*   주의! Entity 클래스와 Entity Repository는 꼭 함께 위치해야함 안그러면 Entity가 역할을 수행하지 못함
*         
* */
public interface PostsRepository extends JpaRepository<Posts, Long>{

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
