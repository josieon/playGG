package com.playdata.springbootproject.domain.posts;

import com.playdata.springbootproject.domain.AuditingEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@Entity
public class Posts extends AuditingEntity{
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    private Long id; //BIGINT
    @Column(length = 500, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT" /*기본은 VARCHAR*/, nullable = false)
    private String content;
    private String author;
@Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
