package com.makeFriends.friends.web.dto;

import com.makeFriends.friends.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsListResponseDto {

    private String title;
    private String author;
    private String content;
    private String position;
    private String tier;


    public PostsListResponseDto(Posts posts){
        this.title = posts.getTitle();
        this.author = posts.getAuthor();
        this.content = posts.getContent();
        this.position = posts.getPosition();
        this.tier = posts.getTier();


    }

}
