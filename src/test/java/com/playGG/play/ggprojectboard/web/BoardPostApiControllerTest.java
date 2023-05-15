package com.playGG.play.ggprojectboard.web;

import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import com.playGG.play.ggprojectboard.domain.boardpost.BoardPostRepository;
import com.playGG.play.ggprojectboard.web.dto.BoardPostSaveRequestDto;
import com.playGG.play.ggprojectboard.web.dto.BoardPostUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //무작위 포트로 충돌 방지
class BoardPostApiControllerTest {

    @LocalServerPort // 현재 실행중 서버 포트 사용하기(무작위 포트 사용)
    private int port;

    //HTTP 요청 및 응답, RestTemplate과 유사한 방식 사용 가능
    @Autowired
    private TestRestTemplate restTemplate; //TestRestTemplate 객체를 사용하여 RESTful 웹 서비스 테스트

    @Autowired
    private BoardPostRepository boardPostRepository;

    @AfterEach
    public void tearDown() throws Exception { //setUp()에서 설정한 리소스나 상태를 정리하고 해제하는 역할
        boardPostRepository.deleteAll();
    }

    @Test
    public void save() throws Exception {
        //given
        String title = "이것은 테스트";  //제목

        Integer viewCount = 1; //조회수

        Integer commentCount = 1;  // 댓글수

        Integer likes = 1; // 좋아요수

        Integer dislike = 1; // 싫어요수

        String contents = "테스트 입니다"; // 게시글 내용

        Integer shareCount = 1; //공유버튼 클릭수 카운트

        String videoUrl = "dd";

        BoardPostSaveRequestDto requestDto = BoardPostSaveRequestDto.builder()
                .title(title)
                .viewCount(viewCount)
                .commentCount(commentCount)
                .likes(likes)
                .dislike(dislike)
                .contents(contents)
                .shareCount(shareCount)
                .videoUrl(videoUrl)
                .build();

        String url = "http://localhost:" + port + "/api/v1/board-post";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<BoardPost> all = boardPostRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getViewCount()).isEqualTo(viewCount);
        assertThat(all.get(0).getCommentCount()).isEqualTo(commentCount);
        assertThat(all.get(0).getLikes()).isEqualTo(likes);
        assertThat(all.get(0).getDislike()).isEqualTo(dislike);
        assertThat(all.get(0).getContents()).isEqualTo(contents);
        assertThat(all.get(0).getShareCount()).isEqualTo(shareCount);
        assertThat(all.get(0).getVideoUrl()).isEqualTo(videoUrl);
    }

    @Test
    public void update() throws Exception {

        // given
        BoardPost savePost = boardPostRepository.save(BoardPost.builder() //데이터 1건 삽입
                        .title("Board")
                        .viewCount(0)
                        .commentCount(0)
                        .likes(0)
                        .dislike(0)
                        .contents("contents")
                        .shareCount(0)
                        .videoUrl("videoUrl")
                        .build()
        );

        Long updatePostId = savePost.getPostId();
        String expectedTitle = "Board2";
        String expectedContents = "contents2";
        String expectedVideoUrl = "http://";

        BoardPostUpdateRequestDto requestDto = BoardPostUpdateRequestDto.builder() //updateDto
                .title(expectedTitle)
                .contents(expectedContents)
                .videoUrl(expectedVideoUrl)
                .build();

        String url = "http://localhost:" + port + "/api/v1/board-post/" + updatePostId;
        // HttpEntity : Spring에서 제공하는 HTTP 요청과 응답에 대한 정보를 담고 있는 클래스, 보통 RestTemplate과 함께 사용
        HttpEntity<BoardPostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange( //RestTemplate의 exchange 메서드를 사용하여, REST API를 호출
                url, // 호출할 REST API의 URL, exchange 메소드를 통해 URL로 요청
                HttpMethod.PUT, //HTTP 메서드(GET, POST, PUT, DELETE 등), PUT으로 보내고
                requestEntity, // 요청에 대한 정보(헤더, 바디), 요청에 대한 정보(DTO 정보)를 함께 전송
                Long.class); // 응답의 타입, 응답 정보는 LONG 타입으로 반환

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<BoardPost> all = boardPostRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContents()).isEqualTo(expectedContents);
        assertThat(all.get(0).getVideoUrl()).isEqualTo(expectedVideoUrl);
    }

}