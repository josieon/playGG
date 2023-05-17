package com.playGG.play.ggprojectboard.domain.boardpost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {

    @Query("SELECT p FROM BoardPost p ORDER BY p.postId DESC")
    List<BoardPost> findAllDesc();
}
