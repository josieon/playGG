package com.playGG.play.ggprojectboard.web.dto;

import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardPostUpdateRequestDto {
    private String title; // 게시글 내용
    private String contents; //공유버튼 클릭수 카운트
    private String videoUrl;

    @Builder
    public BoardPostUpdateRequestDto(String title,
                                     String contents,
                                     String videoUrl) {
        this.title = title;
        this.contents = contents;
        this.videoUrl = videoUrl;
    }

    public BoardPost toEntity() {
        return BoardPost.builder()
                .title(title)
                .contents(contents)
                .videoUrl(videoUrl)
                .build();
    }
}
