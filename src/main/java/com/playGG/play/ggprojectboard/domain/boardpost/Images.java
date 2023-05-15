package com.playGG.play.ggprojectboard.domain.boardpost;

import com.playGG.play.ggprojectboard.domain.AuditingEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor // 빌더패턴 대응
@NoArgsConstructor
@Getter
@Entity
@Table(name = "images") //테이블 명 지정
public class Images extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private BoardPost boardPostImages; //카테고리

    @Column(nullable = false, columnDefinition = "TEXT")
    private String image_Url;

    @OneToOne(mappedBy = "imageComment")
    private Comments comments;

    @OneToOne(mappedBy = "imageReply")
    private Reply reply;
}
