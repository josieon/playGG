package com.playGG.play.ggprojectboard.web.dto;

import com.playGG.play.ggprojectboard.domain.user.Role;
import com.playGG.play.ggprojectboard.domain.user.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

// request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
public class UsersDto {
    // ghldnjs Service 요청 DTO
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long userId;

        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{4,20}$", message = "아이디는 특수문자를 제외한 4~20자리여야 합니다.")
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        private String name;

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;

        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
        private String nickname;

        @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        private String email;

        // DTO -> Entity
        public Users toEntity() {
            return Users.builder()
                    .name(name)
                    .password(password)
                    .nickname(nickname)
                    .email(email)
                    .role(Role.USER)
                    .build();
        }
    }
    // 인증된 사용자 정보를 세션에 저장하는 클래스
    // 세션에 엔티티 클래스 사용시 직렬화를 하면 추후 다른 엔티티와 맵핑 시 다른 엔티티까지 포함되어 성능 이슈가 있음. 세션 저장요 Dto 클래스 생성
    @Getter
    public static class Response implements Serializable { // Serializable : 해당 클래스의 객체를 직렬화할 수 있다는 의미를 전달
        private final Long userId;
        private final String name;
        private final String nickname;
        private final String email;
        private final Role role;


        // 엔티티 -> Dto
        public Response(Users users) {
            this.userId = users.getUserId();
            this.name = users.getName();
            this.nickname = users.getNickname();
            this.email = users.getEmail();
            this.role = users.getRole();
        }
    }
}
