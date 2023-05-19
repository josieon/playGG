package com.playGG.play.ggprojectboard.service;//package com.playGG.play.ggprojectboard.service;
//
//import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
//import com.playGG.play.ggprojectboard.domain.boardpost.BoardPostRepository;
//import com.playGG.play.ggprojectboard.web.dto.BoardPostRequestDto;
//import com.playGG.play.ggprojectboard.web.dto.BoardPostResponseDto;
//import com.playGG.play.ggprojectboard.web.dto.BoardPostsResponseDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j //로그
//@RequiredArgsConstructor
//@Service
//public class BoardPostService {
//
//    private final BoardPostRepository boardPostRepository;
////    private final UsersRepository usersRepository;
//
//
//    // 게시글 등록
////    @Transactional // 메서드 레벨(메소드 단위)에서 사용, 실행 중 예외 발생 시 롤백, ACID
////    public Long
////    save(BoardPostRequestDto dto, String nickname){
////        Users users = userRepository.findByNickname(nickname);
////        dto.setUsers(users);
////        log.info("BoardPostService save()");
////        return boardPostRepository.save(dto.toEntity()).getPostId();
//
//    @Transactional // 메서드 레벨(메소드 단위)에서 사용, 실행 중 예외 발생 시 롤백, ACID
//    public Long save(BoardPostRequestDto dto){
//        log.info("BoardPostService save()");
//        return boardPostRepository.save(dto.toEntity()).getPostId();
//
////        BoardPost boardPost = dto.toEntity();
////        BoardPostRepository.save(boardPost);
////        return boardPost.getPostId();
//    }
//
//    // 게시글 수정
//    @Transactional
//    public Long update(Long postId, BoardPostRequestDto requestDto){
//      BoardPost boardPost = boardPostRepository.findById(postId) //만약 해당하는 게시글이 존재하지 않을 경우 Optional 객체가 반환
//              .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + postId)); //Optional 객체가 감싸는 객체가 null인 경우, IllegalArgumentException 예외를 발생
//        boardPost.update(requestDto.getTitle(), requestDto.getContents()); //postId로 조회된 게시글을 update() 메소드를 호출하여 게시글의 제목과 내용, URL을 수정
//
//        return postId;
////        람다식 사용 전
////        BoardPost boardPost = boardPostRepository.findById(postId)
////            .orElseThrow(new Supplier<IllegalArgumentException>() {
////            @Override
////            public IllegalArgumentException get() {
////                return new IllegalArgumentException("해당 게시글이 없습니다. id=" + id);
////            }
//    }
//
//    @Transactional
//    public BoardPostsResponseDto findPostById(Long postId) {
//        BoardPost responseEntity = boardPostRepository.findById(postId)
//            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + postId));
//
//        return new BoardPostsResponseDto(responseEntity);
//    }
//
//    // 게시글 리스트 조회
//    @Transactional(readOnly = true)
//    public BoardPostsResponseDto findById(Long postId){
//        BoardPost boardPost = boardPostRepository.findById(postId)
//                .orElseThrow(()-> new IllegalStateException("해당 게시글이 존재하지 않습니다. id: " + postId));
//        return new BoardPostsResponseDto(boardPost);
//    }
//
//    @Transactional(readOnly = true) // 수정, 쓰기 발생 예방 및 읽기 전용을 통한 성능향상
//    public List<BoardPostResponseDto> findAllDesc() {
//        return boardPostRepository.findAllDesc().stream()
//                .map(BoardPostResponseDto::new)
//                .collect(Collectors.toList());
//    }
//
//    // 게시글 삭제
//    @Transactional
//    public void delete(Long postId) {
//        BoardPost post = boardPostRepository.findById(postId)
//                .orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다. id=" + postId));
//        boardPostRepository.delete(post);
//    }
//
////    // 조회수 카운트
////    @Transactional
////    public int updateView(Long postId){
////        return boardPostRepository.updateView(postId);
////    }
//
//    // 페이지네이션
//    @Transactional(readOnly = true)
//    public Page<BoardPost> pageList(Pageable pageable) {
//        return boardPostRepository.findAll(pageable);
//    }
//
//    // 검색
//    @Transactional(readOnly = true)
//    public Page<BoardPost> search(String keyword, Pageable pageable) {
//        return boardPostRepository.findByTitleContaining(keyword, pageable);
//    }
//}

import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import com.playGG.play.ggprojectboard.domain.boardpost.BoardPostRepository;
import com.playGG.play.ggprojectboard.web.dto.BoardPostResponseDto;
import com.playGG.play.ggprojectboard.web.dto.BoardPostSaveRequestDto;
import com.playGG.play.ggprojectboard.web.dto.BoardPostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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
        boardPost.update(requestDto.getTitle(), requestDto.getContents()); //postId로 조회된 게시글을 update() 메소드를 호출하여 게시글의 제목과 내용, URL을 수정

        return postId;
//        람다식 사용 전
//        BoardPost boardPost = boardPostRepository.findById(postId)
//            .orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 게시글이 없습니다. id=" + id);
//            }
    }

    @Transactional
    public BoardPostsResponseDto findPostById(Long postId) {
        BoardPost responseEntity = boardPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + postId));

        return new BoardPostsResponseDto(responseEntity);
    }


    @Transactional(readOnly = true) // 수정, 쓰기 발생 예방 및 읽기 전용을 통한 성능향상
    public List<BoardPostResponseDto> findAllDesc() {
        return boardPostRepository.findAllDesc().stream()
                .map(BoardPostResponseDto::new)
                .collect(Collectors.toList());
    }

    public void delete(Long postId) {
        BoardPost post = boardPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다. id=" + postId));
        boardPostRepository.delete(post);
    }
}

