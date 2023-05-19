package com.makeFriends.friends.web;


import com.makeFriends.friends.domain.posts.Posts;
import com.makeFriends.friends.service.PostsService;
import com.makeFriends.friends.web.dto.PostsListResponseDto;
import com.makeFriends.friends.web.dto.PostsResponseDto;
import com.makeFriends.friends.web.dto.PostsSaveRequestDto;
import com.makeFriends.friends.web.dto.SummonerDto;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class PostsApiController {
    private final PostsService postsService;
   /* @Autowired
    public PostsApiController(PostsService postsService){
        this.postsService = postsService;
    }*/



    @PostMapping ("/posts/dto")
    public String save(@RequestBody PostsSaveRequestDto requestDto){
        postsService.save(requestDto);
        return "posts-save" ;
    }

    @GetMapping("/posts/dto")
    public String savePost(){
        return "posts-save";
    }


    @GetMapping({"/posts", "/"})
    public String showDuoSearchBoard(Model model) {
        // Add code here to fetch the duo list data
        List<PostsListResponseDto> duoList = postsService.findAllDesc();
        model.addAttribute("duoList", duoList);
        return "posts";
    }
    @GetMapping("/posts/{position}")
    public String posi(@PathVariable String position , Model model){
        List<PostsListResponseDto> duoList = postsService.posi(position);
        model.addAttribute("duoList", duoList);
        return "posts";
    }









}
