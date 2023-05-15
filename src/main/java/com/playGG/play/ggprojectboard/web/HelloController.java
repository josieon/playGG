package com.playGG.play.ggprojectboard.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //클래스를 JSON을 반환하는 컨트롤러로 지정
public class HelloController {
    @GetMapping("/hello") //HTTP Method 중 GET에 동작하도록 제작
    public String hello(){
        return "hello";
    }
}
