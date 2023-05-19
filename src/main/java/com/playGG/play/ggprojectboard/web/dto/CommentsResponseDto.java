package com.playGG.play.ggprojectboard.web.dto;
import com.playGG.play.ggprojectboard.domain.boardpost.Comments;
import lombok.*;
import org.springframework.stereotype.Component;

    /***
     * 댓글 정보 Response 클래스
     * 엔티티 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환 하여 응답
     * 연관 관계를 맺는 엔티티간의 무한 참조 방지
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Component
    @Getter
    public class CommentsResponseDto {
        private Long commentId;
        private String contents;
        private String nickname;
        private Long userId;
        private Long postId;

        public CommentsResponseDto(Comments comments) {
            this.commentId = comments.getCommentId();
            this.contents = comments.getContents();
            this.nickname = comments.getUsers().getNickname();
            this.userId = comments.getUsers().getUserId();
            this.postId = comments.getBoardPost().getPostId();
        }

    }

