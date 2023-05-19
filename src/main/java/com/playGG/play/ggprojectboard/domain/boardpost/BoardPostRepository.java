package com.playGG.play.ggprojectboard.domain.boardpost;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {

    @Query("SELECT p FROM BoardPost p ORDER BY p.postId DESC")
    List<BoardPost> findAllDesc();

//    @Modifying
//    @Query("update BoardPost p set p.viewCount = p.viewCount + 1 where p.postId = :id")
//    int updateView(Long postId);
    Page<BoardPost> findByTitleContaining(String keyword, Pageable pageable);
}
