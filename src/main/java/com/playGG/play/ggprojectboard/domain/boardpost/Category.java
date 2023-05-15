package com.playGG.play.ggprojectboard.domain.boardpost;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "category") //테이블 명 지정
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, length = 10, unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<BoardPost> boardPostList = new ArrayList<BoardPost>();

    @Builder
    public Category(String categoryName, List<BoardPost> boardPostList) {
        this.categoryName = categoryName;
        this.boardPostList = boardPostList;
    }
}
