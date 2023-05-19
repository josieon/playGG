package com.playGG.play.ggprojectboard.domain.boardpost;

import com.playGG.play.ggprojectboard.domain.AuditingEntity;
import com.playGG.play.ggprojectboard.domain.user.Users;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "comments") //테이블 명 지정
public class Comments extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, columnDefinition = "LONGTEXT") // 500자
    private String contents; // 댓글 내용

//    @Column(nullable = false, columnDefinition = "integer default 0")
//    private Integer likes; // 좋아요수
//
//    @Column(nullable = false, columnDefinition = "integer default 0")
//    private Integer dislike; // 싫어요수

    @ManyToOne
    @JoinColumn(name="post_id")
    private BoardPost boardPost; //해당 게시물

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users; // 작성자

    @OneToOne
    @JoinColumn(name="image_id")
    private Images imageComment;

    @OneToOne
    @JoinColumn(name="reply_id")
    private Reply reply;

    @Builder
    public Comments(String contents,
                    BoardPost boardPost, Users users,
                    Images imageComment, Reply reply) {
        this.contents = contents;
//        this.likes = likes;
//        this.dislike = dislike;
        this.boardPost = boardPost;
        this.users = users;
        this.imageComment = imageComment;
        this.reply = reply;
    }

    // 댓글 수정
    public void update(String contents) {
        this.contents = contents;
    }
}
