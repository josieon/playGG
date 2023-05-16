package com.playGG.play.ggprojectboard.domain.boardpost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {
    @Query("SELECT p FROM BoardPost p ORDER BY p.postId DESC") //JPQL 이용, 엔티티 지정 후 ORDER BY로 정렬 기준 내림차순 정렬
    List<BoardPost> findAllDesc();
}
