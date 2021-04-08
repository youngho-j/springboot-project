package com.youngho.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


/*
* @MappedSuperclass
*  - JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하도록 함
* @EntityListeners(AuditingEntityListener.class)
*  - BaseTimeEntity 클래스에 Auditing 기능 포함 시킴
* @CreatedDate
*  - Entity가 생성되어 저장될 떄 시간이 자동 저장 됨
* @LastModifiedDate
*  - 조회한 Entity의 값을 변경할 때 시간이 자동 저장
*
* */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
