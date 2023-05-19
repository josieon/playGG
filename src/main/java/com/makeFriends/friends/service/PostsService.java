package com.makeFriends.friends.service;

import com.makeFriends.friends.domain.posts.Posts;
import com.makeFriends.friends.domain.posts.PostsRepository;
import com.makeFriends.friends.web.dto.PostsListResponseDto;
import com.makeFriends.friends.web.dto.PostsResponseDto;
import com.makeFriends.friends.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService { // DB에서 값을 가져와서 자바 백엔드 내에서 연산하는 함수)
    private final PostsRepository postsRepository;

    public void save(PostsSaveRequestDto requestDto){
        postsRepository.save(requestDto.toEntity());
    }
    //public 다음 반환타입. 무조건 반환타입은 아님.
    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PostsListResponseDto> posi(String position){
        return postsRepository.findByPosition(position).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

    }







}
