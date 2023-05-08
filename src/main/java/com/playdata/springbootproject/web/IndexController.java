package com.playdata.springbootproject.web;

import com.playdata.springbootproject.config.auth.SessionUser;
import com.playdata.springbootproject.service.PostsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index (Model model) {
        // Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index"; // /src/main/resources/templates/ + "index" + .mustache  뷰 리졸버가 완성해서 머스테치 파일을 찾아서 열어준다.
    }

    @GetMapping("/posts/save")
    public String savePost () {
        return "posts-save";
    }

    @GetMapping("/posts/{id}")
    public String updatePost (Model model, @PathVariable Long id) {
        model.addAttribute("post", postsService.findById(id));
        return "posts-update";
    }





}
