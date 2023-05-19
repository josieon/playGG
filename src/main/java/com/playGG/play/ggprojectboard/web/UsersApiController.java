//package com.playGG.play.ggprojectboard.web;
//
//
//import com.playGG.play.ggprojectboard.service.UsersService;
//import com.playGG.play.ggprojectboard.web.dto.UserJoinRequestDto;
//import com.playGG.play.ggprojectboard.web.dto.UserModifyRequestDto;
//import com.playGG.play.ggprojectboard.web.dto.UsersDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class UsersApiController {
//    private final UsersService usersService;
//    private final AuthenticationManager authenticationManager;
//
//    @PutMapping("/api/v1/users")
//    public ResponseEntity<String> modify(@RequestBody UserModifyRequestDto dto) {
//        usersService.modify(dto);
//
//        // 변경된 세션 등록
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(dto.getName(), dto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("success", HttpStatus.OK);
//        }
//
//}
//
