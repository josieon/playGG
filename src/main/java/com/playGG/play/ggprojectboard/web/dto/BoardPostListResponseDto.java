package com.playGG.play.ggprojectboard.web.dto;

import lombok.Getter;

@Getter
public class BoardPostListResponseDto {
    private String title;
    private String createdAt;

    public BoardPostListResponseDto(String title, String createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }
}
