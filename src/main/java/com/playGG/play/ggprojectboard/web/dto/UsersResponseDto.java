package com.playGG.play.ggprojectboard.web.dto;

import com.playGG.play.ggprojectboard.domain.user.Role;
import com.playGG.play.ggprojectboard.domain.user.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

// request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
    // ghldnjs Service 요청 DTO
    // 인증된 사용자 정보를 세션에 저장하는 클래스
    // 세션에 엔티티 클래스 사용시 직렬화를 하면 추후 다른 엔티티와 맵핑 시 다른 엔티티까지 포함되어 성능 이슈가 있음. 세션 저장요 Dto 클래스 생성
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Component
    public class UsersResponseDto { // Serializable : 해당 클래스의 객체를 직렬화할 수 있다는 의미를 전달
        private Long userId;
        private String name;
        private String nickname;
        private String email;
        private Role role;


        // 엔티티 -> Dto
        public UsersResponseDto(Users users) {
            this.userId = users.getUserId();
            this.name = users.getName();
            this.nickname = users.getNickname();
            this.email = users.getEmail();
            this.role = users.getRole();
        }
    }
