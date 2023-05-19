//package com.playGG.play.ggprojectboard.web;
//
//
//import com.playGG.play.ggprojectboard.config.auth.LoginUser;
//import com.playGG.play.ggprojectboard.service.CommentsService;
//import com.playGG.play.ggprojectboard.web.dto.CommentsDto;
//import com.playGG.play.ggprojectboard.web.dto.UsersDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//public class CommentApiController {
//
//    private final CommentsService commentService;
//
//    /* CREATE */
//    @PostMapping("/api/v1/board-post/{postId}/comments")
//    public ResponseEntity save(@PathVariable Long id, @RequestBody CommentsDto.Request dto,
//                               @LoginUser UsersDto.Response userSessionDto) {
//        return ResponseEntity.ok(commentService.save(id, userSessionDto.getNickname(), dto));
//    }
//
//    /* READ */
//    @GetMapping("/api/v1/board-post/{postId}/comments")
//    public List<CommentsDto.Response> read(@PathVariable Long id) {
//        return commentService.findAll(id);
//    }
//
//    /* UPDATE */
//    @PutMapping({"/api/v1/board-post/{postId}/comments/{id}"})
//    public ResponseEntity update(@PathVariable Long id, @RequestBody CommentsDto.Request dto) {
//        commentService.update(id, dto);
//        return ResponseEntity.ok(id);
//    }
//
//    /* DELETE */
//    @DeleteMapping("/api/v1/board-post/{postId}/comments/{id}")
//    public ResponseEntity delete(@PathVariable Long id) {
//        commentService.delete(id);
//        return ResponseEntity.ok(id);
//    }
//}
