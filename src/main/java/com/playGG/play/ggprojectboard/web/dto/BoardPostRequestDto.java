package com.playGG.play.ggprojectboard.web.dto;

import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import com.playGG.play.ggprojectboard.domain.user.Users;
import lombok.*;
import org.springframework.stereotype.Component;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Component
    public class BoardPostRequestDto{
        private Long postId;
        private String title;
        private String writer;
        private String contents;
        private Integer viewCount;
//        private Users users;

        // 추후 개발
        // private Integer commentCount;  // 댓글수
        // private Integer likes; // 좋아요수
        // private Integer dislike; // 싫어요수
        // private Integer shareCount; //공유버튼 클릭수 카운트

        // Dto -> Entity
        public BoardPost toEntity() {
            return BoardPost.builder()
                    .title(title)
                    .writer(writer==null?"X":writer)
                    .contents(contents)
                    .viewCount(viewCount==null?0:viewCount)
//                    .users(users)
                    .build();
        }
    }
