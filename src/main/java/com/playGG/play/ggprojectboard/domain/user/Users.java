package com.playGG.play.ggprojectboard.domain.user;

import com.playGG.play.ggprojectboard.domain.AuditingEntity;
import com.playGG.play.ggprojectboard.domain.boardpost.BoardPost;
import com.playGG.play.ggprojectboard.domain.boardpost.Comments;
import com.playGG.play.ggprojectboard.domain.boardpost.Reply;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "users") //테이블 명 지정
public class Users extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 25)
    private String nickname;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String passwords;

    @Column(nullable = false, length = 3)
    private String allowing_email;

    @OneToMany(mappedBy = "users")
    private List<BoardPost> boardPostList = new ArrayList<BoardPost>(); // 작성글 리스트

    @OneToMany(mappedBy = "users")
    private List<Comments> commentsList = new ArrayList<Comments>(); // 작성 댓글 리스트

    @OneToMany(mappedBy = "users")
    private List<Reply> replyList = new ArrayList<Reply>(); // 작성 댓글 리스트

    @Builder
    public Users(String nickname, String email, String passwords,
                 String allowing_email, List<BoardPost> boardPostList,
                 List<Comments> commentsList, List<Reply> replyList) {
        this.nickname = nickname;
        this.email = email;
        this.passwords = passwords;
        this.allowing_email = allowing_email;
        this.boardPostList = boardPostList;
        this.commentsList = commentsList;
        this.replyList = replyList;
    }
}
