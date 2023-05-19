package com.playGG.play.ggprojectboard.domain.boardpost;

import com.playGG.play.ggprojectboard.domain.AuditingEntity;
import com.playGG.play.ggprojectboard.domain.user.Users;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@DynamicInsert
//@Builder //lombok 빌더 패턴 구현, PK와 충돌 방지, PK가 있는데 클래스 위에 사용해도 되는지?
//@AllArgsConstructor //Builder 사용을 위한 모든 매개변수 갖는 생성자 생성
@NoArgsConstructor //매개변수가 없는 기본 생성자 자동 생성
@Getter //setter는 데이터의 무결성을 위해 작성하지 않음, 대신 @Builder를 사용하여 DB에 삽입
@Entity //DB의 테이블과 매핑되는 Entity Class 표시, 기본 생성자 및 ID 필수, 필드는 테이블 컬럼과 매핑
@Table(name = "board_post") //테이블 명 지정
public class BoardPost extends AuditingEntity { //AuditingEntity을 통한 일자 자동추가
    @Id //PK 지정, 엔티티의 식별자 역할
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성 식별자값 할당, AUTO_INCREMENT 전략
    private Long postId; //PK

    @Column(length = 30, nullable = false) //데이터 베이스와 매핑될 때 사용 되는 Column 명시
    private String title;  //제목

//    @Column(nullable = false)
//    private String createdAt; //생성일자 , auditing 엔티티로 대체
//
//    @Column
//    private String updatedAt; //수정일자, auditing 엔티티로 대체

    @Column(nullable = false)
    @ColumnDefault("0")//default 0
    private Integer viewCount; //조회수

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer commentCount;  // 댓글수

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer likes; // 좋아요수

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer dislike; // 싫어요수

    @Column(nullable = false, columnDefinition = "LONGTEXT") //이미지를 base64로 받기 위한 LONGTEXT 지정
    private String contents; // 게시글 내용

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer shareCount; //공유버튼 클릭수 카운트

//    @Column(columnDefinition = "TEXT") //NULL 허용
//    private String videoUrl; // 동영상 URL

    @Column
    private String writer;  // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // ForeignKey 추가
    private Users users; //회원

    @ManyToOne
    @JoinColumn(name = "category_id") // ForeignKey 추가
    private Category category; //카테고리

    @OneToMany(mappedBy = "boardPostImages", fetch = FetchType.LAZY) //일대다 관계, 객체 맵핑
    private List<Images> images = new ArrayList<Images>(); //게시글 이미지

    @OneToMany(mappedBy = "boardPost", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)  //일대다 관계, cascade = CascadeType.REMOVE 포스트와 함께 제거
    private List<Comments> comments = new ArrayList<Comments>(); //댓글 리스트

    @Builder
    public BoardPost(String title, Integer viewCount, Integer commentCount,
                     Integer likes, Integer dislike, String contents, Integer shareCount,
                     String writer, Users users, Category category,
                     List<Images> images, List<Comments> comments) {
        this.title = title;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.likes = likes;
        this.dislike = dislike;
        this.contents = contents;
        this.shareCount = shareCount;
        this.writer = writer;
        this.users = users;
        this.category = category;
        this.images = images;
        this.comments = comments;
    }
    // 게시글 수정
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
