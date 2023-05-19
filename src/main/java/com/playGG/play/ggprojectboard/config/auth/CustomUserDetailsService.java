//package com.playGG.play.ggprojectboard.config.auth;
//
//import com.playGG.play.ggprojectboard.domain.user.Users;
//import com.playGG.play.ggprojectboard.domain.user.UsersRepository;
//import com.playGG.play.ggprojectboard.web.dto.UsersDto;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
///**
// * Security User Service
// */
//@RequiredArgsConstructor
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UsersRepository usersRepository;
//
//    private final HttpSession session;
//
//    /* username이 DB에 있는지 확인 */
//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        Users users = usersRepository.findByName(name).orElseThrow(() ->
//                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + name));
//
//        session.setAttribute("users", new UsersDto.Response(users));
//
//        /* 시큐리티 세션에 유저 정보 저장 */
//        return new CustomUserDetails(users);
//    }
//}
