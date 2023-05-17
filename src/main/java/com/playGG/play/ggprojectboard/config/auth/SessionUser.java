package com.playGG.play.ggprojectboard.config.auth;

//엔티티 클래스의 직렬화보다 직렬화 기능을 가진 세션 Dto 클래스를 별도로 작성(보안 이슈)

import com.playGG.play.ggprojectboard.domain.user.Users;
import lombok.Getter;

import java.io.Serializable; //세션 사용을 위한 객체 직렬화(전송가능 상태 변경)

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(Users users) {
        this.name = users.getName();
        this.email = users.getEmail();
        this.picture = users.getPicture();
    }
}
