package com.playGG.play.ggprojectboard.service;

import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import com.playGG.play.ggprojectboard.domain.boardpost.BoardPostRepository;
import com.playGG.play.ggprojectboard.web.dto.BoardPostResponseDto;
import com.playGG.play.ggprojectboard.web.dto.BoardPostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardPostService {

    private final BoardPostRepository boardPostRepository;

    @Transactional // 메서드 레벨(메소드 단위)에서 사용, 실행 중 예외 발생 시 롤백, ACID
    public Long save(BoardPostSaveRequestDto requestDto){
        return boardPostRepository.save(requestDto.toEntity()).getPostId();
    }

    @Transactional
    public Long update(Long postId, BoardPostSaveRequestDto requestDto){
      BoardPost boardPost = boardPostRepository.findById(postId) //만약 해당하는 게시글이 존재하지 않을 경우 Optional 객체가 반환
              .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + postId)); //Optional 객체가 감싸는 객체가 null인 경우, IllegalArgumentException 예외를 발생
        boardPost.update(requestDto.getTitle(), requestDto.getContents(), requestDto.getVideoUrl()); //postId로 조회된 게시글을 update() 메소드를 호출하여 게시글의 제목과 내용, URL을 수정

        return postId;
//        람다식 사용 전
//        BoardPost boardPost = boardPostRepository.findById(postId)
//            .orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 게시글이 없습니다. id=" + id);
//            }
    }
    public BoardPostResponseDto findPostById(Long postId) {
        BoardPost responseEntity = boardPostRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + postId));

        return new BoardPostResponseDto(responseEntity);
    }

}
