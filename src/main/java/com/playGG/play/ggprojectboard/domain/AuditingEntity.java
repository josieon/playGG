package com.playGG.play.ggprojectboard.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //BaseTimeEntity 클래스를 상속받는 클래스에게도 created_at, updated_at 컬럼을 갖게 하기 위함
@EntityListeners(AuditingEntityListener.class) //@CreatedDate, @LastModifiedDate을 사용하기 위함
public class AuditingEntity {
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
