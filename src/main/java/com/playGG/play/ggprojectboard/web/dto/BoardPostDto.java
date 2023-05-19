package com.playGG.play.ggprojectboard.web.dto;

import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import com.playGG.play.ggprojectboard.domain.user.Users;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class BoardPostDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Component
    public static class Request{
        private Long postId;
        private String title;
        private String writer;
        private String contents;
        private Integer viewCount;
        private Users users;

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
                    .users(users)
                    .build();
        }
    }

    // 게시글 정보 Response 클래스, Entity를 Dto로 변환 응답 ,무한참조 방지
    @Getter
    public static class Response{
        private final Long postId;
        private final String title;
        private final String writer;
        private final String contents;
        private final Integer viewCount;
        private final Long userId;
        private final List<CommentsDto.Response> comments; // 코멘트

        public Response(BoardPost boardPost) {
            this.postId = boardPost.getPostId();
            this.title = boardPost.getTitle();
            this.writer = boardPost.getWriter();
            this.contents = boardPost.getContents();
            this.viewCount = boardPost.getViewCount();
            this.userId = boardPost.getUsers().getUserId() ;
            this.comments = boardPost.getComments().stream() //댓글 리스트
                    .map(CommentsDto.Response::new).collect(Collectors.toList());
        }
    }
}
