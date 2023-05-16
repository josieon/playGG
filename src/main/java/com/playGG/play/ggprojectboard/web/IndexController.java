package com.playGG.play.ggprojectboard.web;

import com.playGG.play.ggprojectboard.service.BoardPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller // return값에 따라 View resolver에 의해 mustache로 치환
public class IndexController {
    private final BoardPostService boardPostService;

    @GetMapping("/community") //URL에 표시될 문자
    public String index(Model model){
        // Model를 통한 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
        model.addAttribute("posts",boardPostService.findAllDesc());
        return "index"; // src/main/resources/templates/index.mustache로 치환
    }
    @GetMapping("/community/postsave")
    public String savePost(){
        return "posts-save"; // src/main/resources/templates/posts-save.mustache
    }

    @GetMapping("/community/{postId}")
    public String updatePost(Model model, @PathVariable Long postId){
        model.addAttribute("posts", boardPostService.findPostById(postId));
        return "post-update";
    }
}
