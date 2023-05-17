package com.playGG.play.ggprojectboard.web.dto;


import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardPostResponseDto {
    private Long postId; //PK
    private String title;  //제목
    private Integer viewCount; //조회수
    private Integer commentCount;  // 댓글수
    private Integer likes; // 좋아요수
    private Integer dislike; // 싫어요수
    private String contents; // 게시글 내용
    private Integer shareCount; //공유버튼 클릭수 카운트
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String videoUrl;



    public BoardPostResponseDto(BoardPost responseEntity) {
        this.postId = responseEntity.getPostId();
        this.title = responseEntity.getTitle();
        this.viewCount = responseEntity.getViewCount();
        this.commentCount = responseEntity.getCommentCount();
        this.likes = responseEntity.getLikes();
        this.dislike = responseEntity.getDislike();
        this.contents = responseEntity.getContents();
        this.shareCount = responseEntity.getShareCount();
        this.createdAt = responseEntity.getCreatedAt();
        this.updatedAt = responseEntity.getUpdatedAt();
        this.videoUrl = responseEntity.getVideoUrl();
    }
}
