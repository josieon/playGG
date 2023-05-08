package com.playdata.springbootproject.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 소셜 로그인으로 반환되는 값 중 email 통해서 이미 생성된 사용자인지 판단하기 위해서 email을 기준으로 조회
     * @param email
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);
}
