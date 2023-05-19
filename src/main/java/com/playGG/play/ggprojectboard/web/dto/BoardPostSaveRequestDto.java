package com.playGG.play.ggprojectboard.web.dto;

import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardPostSaveRequestDto { //HTTP Request Body로 보내는 값(JSON)을 담기 위한 DTO

    private String title;  //제목
    private Integer viewCount; //조회수
    private Integer commentCount;  // 댓글수
    private Integer likes; // 좋아요수
    private Integer dislike; // 싫어요수
    private String contents; // 게시글 내용
    private Integer shareCount; //공유버튼 클릭수 카운트
//    private String videoUrl;

    @Builder
    public BoardPostSaveRequestDto(String title, Integer viewCount,
                     Integer commentCount, Integer likes, Integer dislike, String contents,
                     Integer shareCount) {
        this.title = title;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.likes = likes;
        this.dislike = dislike;
        this.contents = contents;
        this.shareCount = shareCount;
    }

    public BoardPost toEntity() {     //BoardPostSaveRequestDto 객체를 BoardPost 엔티티로 변환
        return BoardPost.builder() // 빌더를 사용하여 BoardPost 객체의 각 필드에는 BoardPostSaveRequestDto 객체의 해당 필드 값을 설정
                .title(title)
                .viewCount(viewCount==null?0:viewCount) // null로 잡히는 현상 대응
                .commentCount(commentCount==null?0:commentCount)
                .likes(likes==null?0:likes)
                .dislike(dislike==null?0:dislike)
                .contents(contents)
                .shareCount(shareCount==null?0:shareCount)
                .build(); //.build() 메서드를 호출하여 BoardPost 객체를 최종적으로 생성
    }
}