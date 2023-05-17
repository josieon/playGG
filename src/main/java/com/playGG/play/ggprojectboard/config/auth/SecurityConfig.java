package com.playGG.play.ggprojectboard.config.auth;


import com.playGG.play.ggprojectboard.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security를 설정들을 활성화시켜줌
@Configuration // Spring 설정 클래스 표시
// Spring Security를 설정 클래스
public class SecurityConfig {
    private final CustomOauth2UserService customOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // csrf() 메소드는 CSRK(Cross-Site Request Forgery) 공격 방지를 위한 설정하는 역할
                // 위 기능을 사용하면 제약이 많아짐으로 이 메소드의 disable()을 호출하여 비활성화
                .csrf().disable()
                // headers() 메소드는 HTTP 응답 헤더를 설정
                // 이 메소드의 frameOptions().disable()를 호출하여 X-Frame-Options 헤더 설정을 비활성화
                .headers().frameOptions().disable()
                .and()
                    //URL 별 권한 관리를 설정하는 옵션의 시작점
                    .authorizeHttpRequests()
                    //권한 관리 대상을 지정하는 옵션
                    // URL 및 HTTP 메소드 별로 관리가 가능
                    // "/" 등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 줄 수 있음
                    // 권한 관리 대상을 URL로 지정, 아래 패턴의 URL에 대해서 전체 접근하는 사용자에 대해 모두 허가
                    .requestMatchers("/community", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                    .requestMatchers("/login").permitAll()
                    // USER라고 하는 권한 가진 사람만 "/api/v1/**" 하위 URL에 대해서 허가
                    .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 설정된 값들 이외 나머지 URL들을 나타낸다.
                    .anyRequest().authenticated()
                    .and()

                        // 로그아웃 기능에 대한 여러 설정의 진입점
                       .logout()
                          .logoutSuccessUrl("/community")
                    .and()
                        // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                         .oauth2Login()
                             .defaultSuccessUrl("/community")
                            // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                             .userInfoEndpoint()
                                // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체를 등록
                                .userService(customOauth2UserService);
        return  http.build();
    }
}
