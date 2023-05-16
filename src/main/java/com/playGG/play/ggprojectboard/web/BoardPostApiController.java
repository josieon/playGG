package com.playGG.play.ggprojectboard.web;

import com.playGG.play.ggprojectboard.service.BoardPostService;
import com.playGG.play.ggprojectboard.web.dto.BoardPostResponseDto;
import com.playGG.play.ggprojectboard.web.dto.BoardPostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController //rest 방식
public class BoardPostApiController {

    private final BoardPostService boardPostService;

    //저장
    @PostMapping("/api/v1/board-post")
    public Long save(@RequestBody BoardPostSaveRequestDto requestDto) { //@RequestBody는 클라이언트에서 보낸 JSON 혹은 XML을 자바객체로 변환
        return boardPostService.save(requestDto);
    }

    //수정
    @PutMapping("/api/v1/board-post/{postId}") // HTTP PUT 요청 처리 엔드포인트, @PathVariable 어노테이션으로 {postId}값을 추출
    public Long update(@PathVariable Long postId, @RequestBody BoardPostSaveRequestDto requestDto){
        return boardPostService.update(postId, requestDto);
    }

    //조회
    @GetMapping("/api/v1/board-post/{postId}")
    public BoardPostResponseDto findPostById(@PathVariable Long postId){
        return boardPostService.findPostById(postId);
    }

    //삭제
    @DeleteMapping("/api/v1/board-post/{postId}")
    public Long delete(@PathVariable Long postId){
        boardPostService.delete(postId);
        return postId;
    }

}

