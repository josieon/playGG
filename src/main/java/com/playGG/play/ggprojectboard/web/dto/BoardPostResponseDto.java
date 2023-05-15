package com.playGG.play.ggprojectboard.web.dto;


import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardPostResponseDto {
    private Long postId; //PK
    private String title;  //제목
//    private String createdAt; //생성일자 , auditing 엔티티로 대체
//    private String updatedAt; //수정일자, auditing 엔티티로 대체
    private Integer viewCount; //조회수
    private Integer commentCount;  // 댓글수
    private Integer likes; // 좋아요수
    private Integer dislike; // 싫어요수
    private String contents; // 게시글 내용
    private Integer shareCount; //공유버튼 클릭수 카운트

    public BoardPostResponseDto(BoardPost responseEntity) {
        this.postId = responseEntity.getPostId();
        this.title = responseEntity.getTitle();
        this.viewCount = responseEntity.getViewCount();
        this.commentCount = responseEntity.getCommentCount();
        this.likes = responseEntity.getLikes();
        this.dislike = responseEntity.getDislike();
        this.contents = responseEntity.getContents();
        this.shareCount = responseEntity.getShareCount();
    }
}
