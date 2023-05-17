package com.playGG.play.ggprojectboard.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Spring Security를 사용하여 인증 및 인가를 처리할 때
// 사용할 사용자 권한(Role)을 정의한 Enum 클래스인 Role 클래스를 정의
@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST","Guest"),
    USER("ROLE_USER","User");

    private final String key; // 권한 코드(ROLE_)
    private final String title; //실제 권한 이름
}
