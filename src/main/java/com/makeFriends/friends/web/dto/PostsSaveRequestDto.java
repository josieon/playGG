package com.makeFriends.friends.web.dto;

import com.makeFriends.friends.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostsSaveRequestDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String position;
    private String tier;


    public Posts toEntity(){
        return Posts.builder()
                .id(id)
                .title(title)
                .content(content)
                .author(author)
                .position(position)
                .tier(tier)
                .build();


    }


}
