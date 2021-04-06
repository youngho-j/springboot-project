package com.youngho.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
* 어노테이션 순서 - 주요 어노테이션을 클래스에 가깝도록
*
* @Getter - 클래스 내 모든 필드의 Getter 메소드 자동 생성
* @NoArgsConstructor - 기본 생성자 자동 추가
* @Entity - 테이블과 링크될 클래스임을 명시
*           카멜케이스 명명 규칙(클래스 - 대문자시작, 명사 사용)을 따른 클래스 이름을 언더스코어 네이밍으로 테이블 이름 매칭
*           Ex) SalesManger -> sales_manager
 */

@Getter
@NoArgsConstructor
@Entity
/*
* 실제 DB와 매칭될 클래스
*
* 현재 이클래스에는 setter 메소드가 없음
* 만약 자바빈 규약을 생각하며, getter. setter를 무작정 생성하게되면
* 해당 클래스의 인스턴스 값들이 언제 어디서 변해야하는지 코드상으로 명확하게 구분할 수 없음 -> 기능 변경시 복잡해짐
*
* 따라서 Entity 클래스에서는 절대 setter 메소드를 만들지 않음
* 그럼 값을 어떻게 변경하고 채워?
*
* 값 변경 필요시는 명확하게 목적과 의도를 나타낼 수 있는 메소드 추가 필요
* 값을 채울때는 생성자를 통해 최종값을 채운 뒤 DB에 삽입
 */
public class Posts {
    /*
     *  @Id - 해당 테이블의 PK 필드를 나타냄
     *  @GeneratedValue - PK 생성 규칙을 나타냄
     *                    스프링 부트 버전별로 차이 있음
     *                    2.0에서는 GenerationType.IDENTITY를 추가해야 auto increment 가능
     *  참고
     *  @Entity의 PK는 웬만하면 Long 타입 auto_increment를 추전
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    *   @Column - 테이블의 칼럼을 나타냄, 선언하지 않아도 모두 칼럼이 되나,
    *             추가적으로 변경이 필요한 옵션이 있으면 사용
    * */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;
    /*
    *   @Builder - 해당 클래스의 빌더 패턴 클래스 생성
    * 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    *
    * 참고 생성자와 @Builder의 차이
    * 공통 - 생성 시점에 값을 채워주는 역할을 함
    * 차이 - 생성자 : 지금 채워야할 필드가 무엇인지 명확히 지정 불가
    *     - @Builder : 어느필드에 어떤 값을 채워야 할지 명확히 인지 가능
    * */
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }


}

/*

 */
