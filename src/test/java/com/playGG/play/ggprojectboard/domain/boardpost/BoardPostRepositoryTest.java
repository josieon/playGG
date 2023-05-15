package com.playGG.play.ggprojectboard.domain.boardpost;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) // Spring TestContext Framework를 활성화
@SpringBootTest //Spring ApplicationContext를 로드, 시작 및 테스트
class BoardPostRepositoryTest {
    @Autowired
    BoardPostRepository boardPostRepository; //테스트로 필드 주입

    @AfterEach //테스트에 사용된 리소스나 객체들을 정리하거나 초기화
    public void cleanup(){
        boardPostRepository.deleteAll();
    }

    @Test
    public void saveAndLoad(){
        //given
        String title = "테스트 게시글";  //제목

        String createdAt = "오늘 생성"; //생성일자 , auditing 엔티티로 대체

        String updatedAt = "오늘 삭제 예정"; //수정일자, auditing 엔티티로 대체

        Integer viewCount = 1; //조회수

        Integer commentCount = 1;  // 댓글수

        Integer likes = 1; // 좋아요수

        Integer dislike = 1; // 싫어요수

        String contents = "도라지타령"; // 게시글 내용

        Integer shareCount = 1; //공유버튼 클릭수 카운트

        String videoUrl = "uer//:ddd.com"; // 동영상 URL

        boardPostRepository.save(BoardPost.builder()
                .title(title)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .viewCount(viewCount)
                .commentCount(commentCount)
                .likes(likes)
                .dislike(dislike)
                .contents(contents)
                .shareCount(shareCount)
                .videoUrl(videoUrl)
                .build()
        );

        //whew
        BoardPost boardPost = boardPostRepository.findAll().get(0);

        //then
        assertEquals(boardPost.getTitle(), title);
        assertEquals(boardPost.getCreatedAt(), createdAt);
        assertEquals(boardPost.getUpdatedAt(), updatedAt);
        assertEquals(boardPost.getViewCount(), viewCount);
        assertEquals(boardPost.getCommentCount(), commentCount);
        assertEquals(boardPost.getLikes(), likes);
        assertEquals(boardPost.getDislike(), dislike);
        assertEquals(boardPost.getContents(), contents);
        assertEquals(boardPost.getShareCount(), shareCount);
        assertEquals(boardPost.getVideoUrl(), videoUrl);
    }

}