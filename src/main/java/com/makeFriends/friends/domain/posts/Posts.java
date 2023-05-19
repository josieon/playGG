package com.makeFriends.friends.domain.posts;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @Column(length = 30, nullable = false)
    private String author;
    private String position;
    private String tier;





    @Builder
    public Posts(Long id,String title, String content, String author, String position ,String tier){
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.position = position;
        this.tier = tier;
    }


}
