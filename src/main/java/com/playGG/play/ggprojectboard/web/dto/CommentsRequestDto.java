package com.playGG.play.ggprojectboard.web.dto;

import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import com.playGG.play.ggprojectboard.domain.boardpost.Comments;
import com.playGG.play.ggprojectboard.domain.user.Users;
import lombok.*;
import org.springframework.stereotype.Component;

    // 댓글 Service 요청 DTO 클래스
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Component
    public  class CommentsRequestDto {
        private Long commentId;
        private String contents;
        private Users users;
        private BoardPost boardPost;

        // DTO를 Entity로 변환
        public Comments toEntity(){
            return Comments.builder()
                    .contents(contents)
                    .users(users)
                    .boardPost(boardPost)
                    .build();
        }
    }
