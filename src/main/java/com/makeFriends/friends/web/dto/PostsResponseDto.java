package com.makeFriends.friends.web.dto;

import com.makeFriends.friends.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String position;
    private String tier;


    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.position = entity.getPosition();
        this.tier = entity.getTier();

    }
}
