package com.playdata.springbootproject.web;

import com.playdata.springbootproject.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String Hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam(value = "name", defaultValue = "홍길동") String name,
                                     @RequestParam(value = "amount", defaultValue = "100") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
