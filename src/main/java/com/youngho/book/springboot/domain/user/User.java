package com.youngho.book.springboot.domain.user;

import com.youngho.book.springboot.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    /*
    *   @Enumerated(EnumType.STRING)
    *   JPA로 데이터베이스를 저장할때 Enum 값을 어떤 형태로 저장할지 결정
    *   기본적으로 int로 된 숫자가 저장
    *   슷자로 저장시 그 값이 무슨 코드를 의미하는지 알 없으므로 문자열로 저장될 수 있도록 선언
    *
    *   각 사용자의 권한을 관리할 Enum 클래스 Role 생성성    * */

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
