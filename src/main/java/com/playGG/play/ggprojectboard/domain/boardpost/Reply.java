package com.playGG.play.ggprojectboard.domain.boardpost;
import com.playGG.play.ggprojectboard.domain.AuditingEntity;
import com.playGG.play.ggprojectboard.domain.user.Users;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "reply") //테이블 명 지정
public class Reply extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column(nullable = false, length = 500)
    private String contents;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer likes; // 좋아요수

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer dislike; // 싫어요수

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;

    @OneToOne
    @JoinColumn(name="image_id")
    private Images imageReply;

    @OneToOne(mappedBy = "reply")
    private Comments comments;

    @Builder

    public Reply(String contents, Integer likes,
                 Integer dislike, Users users,
                 Images imageReply, Comments comments) {
        this.contents = contents;
        this.likes = likes;
        this.dislike = dislike;
        this.users = users;
        this.imageReply = imageReply;
        this.comments = comments;
    }
}
