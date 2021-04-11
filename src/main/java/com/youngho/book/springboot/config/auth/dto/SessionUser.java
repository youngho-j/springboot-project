package com.youngho.book.springboot.config.auth.dto;

import com.youngho.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/*
*   인증된 사용자 정보만 필요
*   왜 따로 생성을 했는가?
*   생성해놓은 User 클래스 사용시 - 클래스에 직렬화 구현하지 않았다는 에러 발생
*   그럼 에러를 해결하기 위해 직렬화 코드 삽입을 하면 해결이 되는가?
*   이 해결 방법에 대해 다시 한번 생각을 해 볼 필요가 있음
*   User 클래스는 엔티티이기 때문에 언제 다른 엔티티와 관계가 형성될지 모름
*   만약 @OneToMany 등 자식 엔티티를 가지고 있다고 하면 직렬화 대상에 자식 들도 포함됨
*   그렇게 된다면 성능 상 문제가 발생하거나 부수적인 효과가 발생될 확률이 높아짐
*
*   따라서, 직렬화 기능을 가진 세션Dto를 하나 추가로 만드는 것이 좋음
*   이렇게 추가를 하게 되면 이후 운영 및 유지보수시 많은 도움이 됨
* */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
