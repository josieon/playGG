package com.playGG.play.ggprojectboard.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    /**
     * 소셜 로그인으로 반환되는 값 email 필드를 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판다하는 메소드
     * @param email
     * @return Optional<Users>
     */
    // OAuth
    Optional<Users> findByEmail(String email); //명명 규칙에 따라 JPA가 처리

    // Security
    Optional<Users> findByName(String name);

    // user name
    Users findByNickname(String nickname);

    // 아이디 중복 검사, 중복 true, 중복이 아닌 경우 false
    boolean existsByName(String name);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);


}
