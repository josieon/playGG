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

// 간편 로그인을 통해 가입한 유저
@NoArgsConstructor
@Getter
@Entity
@Table(name = "users") //테이블 명 지정
public class Users extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid; // 회원 번호

    @Column
    private String name; // 회원 닉네임

    @Column
    private String email; //이메일주소

//    @Column(nullable = false, length = 3)
//    private String allowing_email; 추가 구현 예정
    // 등급 추가 예정
    @Column
    private String picture;

    @OneToMany(mappedBy = "users")
    private List<BoardPost> boardPostList = new ArrayList<BoardPost>(); // 작성글 리스트

    @OneToMany(mappedBy = "users")
    private List<Comments> commentsList = new ArrayList<Comments>(); // 작성 댓글 리스트

    @OneToMany(mappedBy = "users")
    private List<Reply> replyList = new ArrayList<Reply>(); // 작성 댓글 리스트

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Users(String name, String email, String picture,
                 List<BoardPost> boardPostList, List<Comments> commentsList,
                 List<Reply> replyList, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.boardPostList = boardPostList;
        this.commentsList = commentsList;
        this.replyList = replyList;
        this.role = role;
    }

    /**
     * 사용자 정보를 업데이트
     * @param name, picture
     * @return 업데이트된 사용자 정보
     */
    public Users update(String name, String picture){
        this.name = name;
        this.picture = picture;
        return this;
    }

    /**
     * 사용자 권한 가져오기
     * @return 사용자 권한
     */
    public String getRoleKey(){
        return this.role.getKey();
    }

}
