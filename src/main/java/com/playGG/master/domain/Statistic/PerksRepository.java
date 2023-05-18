package com.playGG.master.domain.Statistic;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PerksRepository extends JpaRepository<perk_statistic, Integer> {
    @Query("SELECT p FROM perk_statistic p")
    List<perk_statistic> findAll();

    @Query("SELECT distinct p FROM perk_statistic p WHERE p.perkPK.championId = :id ORDER BY p.choices desc limit 10")
    List<perk_statistic> findAllById(@Param("id") int id);
}
