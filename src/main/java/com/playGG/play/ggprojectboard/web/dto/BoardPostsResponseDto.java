package com.playGG.play.ggprojectboard.web.dto;

import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import lombok.*;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
    // 게시글 정보 Response 클래스, Entity를 Dto로 변환 응답 ,무한참조 방지
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Component
    @Getter
    public class BoardPostsResponseDto{
        private Long postId;
        private String title;
        private String writer;
        private String contents;
        private Integer viewCount;
        private Long userId;
        private LocalDateTime createdAt;
        private List<CommentsResponseDto> comments; // 코멘트

        public BoardPostsResponseDto(BoardPost boardPost) {
            this.postId = boardPost.getPostId();
            this.title = boardPost.getTitle();
            this.writer = boardPost.getWriter();
            this.contents = boardPost.getContents();
            this.viewCount = boardPost.getViewCount();
            this.createdAt = boardPost.getCreatedAt();
//            this.userId = boardPost.getUsers().getUserId() ;
            this.comments = boardPost.getComments().stream() //댓글 리스트
                    .map(CommentsResponseDto::new).collect(Collectors.toList());
        }
    }
