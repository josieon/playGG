//package com.playGG.play.ggprojectboard.service;
//
//import com.playGG.play.ggprojectboard.domain.user.Users;
//import com.playGG.play.ggprojectboard.domain.user.UsersRepository;
//import com.playGG.play.ggprojectboard.web.dto.UserJoinRequestDto;
//import com.playGG.play.ggprojectboard.web.dto.UserModifyRequestDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.Errors;
//import org.springframework.validation.FieldError;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RequiredArgsConstructor
//@Service
//public class UsersService {
//    private final UsersRepository usersRepository;
//    private final BCryptPasswordEncoder encoder;
//
//    // 회원가입
//    @Transactional
//    public void usersJoin(UserJoinRequestDto dto) {
//        dto.setPassword(encoder.encode(dto.getPassword()));
//        usersRepository.save(dto.toEntity());
//    }
//
//    //회원가입 중복 체크, 유효성 검사
//    @Transactional(readOnly = true)
//    public Map<String, String> validateHandling(Errors errors) {
//        Map<String, String> validateResult = new HashMap<>();
//
//        // 유효성 검사, 중복 검사에 실패한 필드 목록
//        for(FieldError error : errors.getFieldErrors()) {
//            String validKeyName = String.format("valid_%s", error.getField());
//            validateResult.put(validKeyName, error.getDefaultMessage());
//        }
//        return validateResult;
//    }
//
//    // 회원정보 수정
//    /* 회원수정 (dirty checking) */
//    @Transactional
//    public void modify(UserModifyRequestDto dto) {
//        Users users = usersRepository.findById(dto.toEntity().getUserId())
//                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
//        String encPassword = encoder.encode(dto.getPassword());
//        users.modify(dto.getNickname(), encPassword);
//    }
//}
