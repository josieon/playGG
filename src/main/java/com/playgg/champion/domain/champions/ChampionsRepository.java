package com.playgg.champion.domain.champions;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface ChampionsRepository extends JpaRepository<champion_statistic, Integer> {
    @Query("select c from champion_statistic c")
    List<champion_statistic> findAll();
}
