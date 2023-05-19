package com.playGG.play.ggprojectboard.config.auth;

import com.playGG.play.ggprojectboard.domain.user.Role;
import com.playGG.play.ggprojectboard.domain.user.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class OauthAttributes {
    private Map<String, Object> attributes; //사용자 정보들이 모두 담겨 있음
    private String nameAttributeKey; //사용자 이름
    private String name; // 사용자 이름
    private String email; // 사용자 이메일
//    private String picture; // 프로필 사진
    private String nickname;


    public static OauthAttributes of(String registration,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OauthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes){
        return OauthAttributes.builder()
                .name((String) attributes.get("email"))
                .email((String) attributes.get("email"))
                .nickname((String) attributes.get("name"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Users toEntity(){
        return Users.builder()
                .name(email)
                .email(email)
                .nickname(nickname)
                .role(Role.GUEST)
                .build();
    }
}
