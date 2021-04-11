package com.youngho.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    /*
    *   스프링 시큐리티에서는 권한 코드에 항상 ROLE_ 이 앞에 존재해야함
    *   코드별 키 값을 ROLE_XXX 으로 지정
    * */
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}
