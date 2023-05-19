package com.makeFriends.friends.web;

import com.makeFriends.friends.service.PostsService;
import com.makeFriends.friends.web.dto.PostsListResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping
public class PostsController {
    @Autowired
    private PostsService postsService;

    @Value("${apikey}")
    private String API_KEY;


    RestTemplate restTemplate = new RestTemplate();






}
