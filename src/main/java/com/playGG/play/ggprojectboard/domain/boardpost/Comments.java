package com.playGG.play.ggprojectboard.domain.boardpost;

import com.playGG.play.ggprojectboard.domain.user.Users;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "comments") //테이블 명 지정
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, length = 500)
    private String contents;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer likes; // 좋아요수

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer dislike; // 싫어요수

    @Column(nullable = false)
    private String createdAt; //생성일자

    @Column(nullable = false)
    private String updatedAt; //수정일자

    @ManyToOne
    @JoinColumn(name="post_id")
    private BoardPost boardPost;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;

    @OneToOne
    @JoinColumn(name="image_id")
    private Images imageComment;

    @OneToOne
    @JoinColumn(name="reply_id")
    private Reply reply;

    @Builder
    public Comments(String contents, Integer likes, Integer dislike,
                    String createdAt, String updatedAt, BoardPost boardPost,
                    Users users, Images imageComment, Reply reply) {
        this.contents = contents;
        this.likes = likes;
        this.dislike = dislike;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.boardPost = boardPost;
        this.users = users;
        this.imageComment = imageComment;
        this.reply = reply;
    }
}
