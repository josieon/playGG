//package com.playGG.play.ggprojectboard.config;
//
//import com.playGG.play.ggprojectboard.domain.user.UsersRepository;
//import com.playGG.play.ggprojectboard.web.dto.UsersDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//
///**
// * 중복 확인 유효성 검증을 위한 커스텀 Validator 클래스
// */
//@RequiredArgsConstructor
//@Component
//public class CustomValidators {
//
//    @RequiredArgsConstructor
//    @Component
//    public static class EmailValidator extends AbstractValidator<UsersDto.Request> {
//        private final UsersRepository userRepository;
//
//        @Override
//        protected void doValidate(UsersDto.Request dto, Errors errors) {
//            if (userRepository.existsByEmail(dto.toEntity().getEmail())) {
//                errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
//            }
//        }
//    }
//
//    @RequiredArgsConstructor
//    @Component
//    public static class NicknameValidator extends AbstractValidator<UsersDto.Request> {
//        private final UsersRepository userRepository;
//
//        @Override
//        protected void doValidate(UsersDto.Request dto, Errors errors) {
//            if (userRepository.existsByNickname(dto.toEntity().getNickname())) {
//                errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임 입니다.");
//            }
//        }
//    }
//
//    @RequiredArgsConstructor
//    @Component
//    public static class UsernameValidator extends AbstractValidator<UsersDto.Request> {
//        private final UsersRepository userRepository;
//
//        @Override
//        protected void doValidate(UsersDto.Request dto, Errors errors) {
//            if (userRepository.existsByName(dto.toEntity().getName())) {
//                errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
//            }
//        }
//    }
//}
