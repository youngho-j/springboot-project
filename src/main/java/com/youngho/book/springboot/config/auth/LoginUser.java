package com.youngho.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}

/*
 *   @Target(ElementType.PARAMETER)
 *   - 어노테이션이 생성될 수 있는 위치 지정
 *   - 타입을 PARAMETER로 지정 즉, 메소드의 파라미터로 선언된 객체에서만 사용 가능
 *
 *   @interface
 *   - 해당 파일을 어노테이션 클래스로 지정
 *   - LoginUser 라는 이름을 가진 어노테이션이 생성되었다고 보면 됨
 *
 */
