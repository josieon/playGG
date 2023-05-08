package com.playdata.springbootproject.service;

import com.playdata.springbootproject.domain.posts.Posts;
import com.playdata.springbootproject.domain.posts.PostsRepository;
import com.playdata.springbootproject.web.dto.PostsListResponseDto;
import com.playdata.springbootproject.web.dto.PostsResponseDto;
import com.playdata.springbootproject.web.dto.PostsSaveRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public long save(PostsSaveRequestDto requrestDto) {
        return postsRepository.save(requrestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, PostsSaveRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. id=" +id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. id=" +id));
        return new PostsResponseDto(posts);
    }
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
@Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. id = " + id));
        postsRepository.delete(posts);
    }
}