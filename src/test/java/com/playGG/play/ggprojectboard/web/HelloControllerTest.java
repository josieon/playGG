package com.playGG.play.ggprojectboard.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class) //Spring Framework 테스트를 위한 설정
@WebMvcTest(controllers = HelloController.class) //컨트롤러만 테스트를 위한 설정
@Import(SecurityConfig.class)
class HelloControllerTest {
    @Autowired //스프링이 관리하는 빈 주입, 테스트 때는 필드 주입으로 진행
    private MockMvc mvc; //웹 API테스트를 할 때 사용, 스프링 MVC 테스트의 시작점 (GET, POST 등  API 테스트 가능)

    @Test //테스트 메소드로 설정
    void hello() throws Exception {
        //given
        String hello = "hello";

        //when, then
        //테스트 코드 작성, perform은 MockMvc를 통해 GET요청 수행
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk()) //HTTP 상태 코드 200 확인
                .andExpect(MockMvcResultMatchers.content().string(hello)); //본문 내용 hello 검증

    }
}