//package com.playGG.play.ggprojectboard.service;
//
//import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
//import com.playGG.play.ggprojectboard.domain.boardpost.BoardPostRepository;
//import com.playGG.play.ggprojectboard.domain.boardpost.Comments;
//import com.playGG.play.ggprojectboard.domain.boardpost.CommentsRepository;
//import com.playGG.play.ggprojectboard.domain.user.Users;
//import com.playGG.play.ggprojectboard.domain.user.UsersRepository;
//import com.playGG.play.ggprojectboard.web.dto.CommentsDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Service
//public class CommentsService {
//    private final CommentsRepository commentRepository;
//    private final UsersRepository userRepository;
//    private final BoardPostRepository boardpostRepository;
//
//    @Transactional
//    public Long save(Long postId, String nickname, CommentsDto.Request dto) {
//
//        // 댓글 생성
//        BoardPost boardPost = boardpostRepository.findById(postId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다" + postId));
//        Users users = userRepository.findByNickname(nickname);
//
//        dto.setUsers(users);
//        dto.setBoardPost(boardPost);
//
//        Comments comments = dto.toEntity();
//        commentRepository.save(comments);
//
//        return comments.getCommentId();
//        //return commentRepository.save(dto.toEntity()).getCommentId();
//        }
//
//        // 댓글 조회
//        @Transactional(readOnly = true)
//        public List<CommentsDto.Response> findAll(Long postId){
//            BoardPost Post = boardpostRepository.findById(postId)
//                    .orElseThrow(() -> new IllegalStateException("해당 게시글이 존재하지 않습니다" + postId));
//            List<Comments> comments = Post.getComments();
//            return comments.stream()
//                    .map(CommentsDto.Response::new)
//                    .collect(Collectors.toList());
//        }
//
//        // 댓글 수정
//        @Transactional
//        public void update(Long commentId, CommentsDto.Request dto){
//            Comments comments = commentRepository.findById(commentId)
//                    .orElseThrow(() -> new IllegalStateException("해당 댓글이 존재하지 않습니다. " + commentId));
//            comments.update(dto.getContents());
//        }
//
//        // 댓글 삭제
//        @Transactional
//        public void delete(Long commentId) {
//            Comments comments = commentRepository.findById(commentId)
//                    .orElseThrow(() -> new IllegalStateException("해당 댓글이 존재하지 않습니다. id=" + commentId));
//            commentRepository.delete(comments);
//        }
//}
