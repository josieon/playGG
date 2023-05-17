package com.playGG.play.ggprojectboard.config.auth;

import com.playGG.play.ggprojectboard.domain.user.UsersRepository;
import com.playGG.play.ggprojectboard.domain.user.Users;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

//OAuth2 로그인을 처리 서비스 클래스
@RequiredArgsConstructor
@Service
public class CustomOauth2UserService implements OAuth2UserService { //OAuth2UserService: Provider로 받은 인증된 사용자 정보 객체 생성
    private final UsersRepository usersRepository;
    private final HttpSession httpsession;

    /**
     * 구글 로그인 이후 가져온 사용자의 정보(email, name, picture 등)을 기반으로 가입 및 정보수정, 세션 저장 등의 기능을 지원
     * @param userRequest the user request
     * @Return OAuth2User
     * @throws OAuth2AuthenticationException
     * */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService(); //DefaultOAuth2UserService 객체를 생성하여 OAuth2 공급자로부터 받은 사용자 정보 받음

        // OAuth2UserService를 통해 가져온 OAuth2User
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행 중인 서비스를 구분하는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // OAurh2 로그인 진행 시 키가 되는 필드 값, PK와 같은 의미
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        // OAurh2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        OauthAttributes attributes = OauthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // OAuthAttributes를 통해 가져온 attribute를 이용하여 사용자 정보를 저장
        Users users = saveOrUpdate(attributes);

        // SesseionUser: 세션에 사용자 정보를 저장하기 위한 Dto 클래스
        httpsession.setAttribute("users", new SessionUser(users));

        // DefaultOAuth2User: OAuth2User의 구현체이며, OAuth2User를 구현하고 있는 DefaultOAuth2User의 생성자를 통해 권한 설정
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(users.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    /**
     * OAuth2UserService를 통해 가져온 OAuth2User의 Attributes를 DB에 저장(업데이트)
     * @param attributes
     * @return User
     * **/
    private Users saveOrUpdate(OauthAttributes attributes) {
        Users users = usersRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return usersRepository.save(users);
    }
}
