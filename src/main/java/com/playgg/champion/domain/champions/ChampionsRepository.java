package com.playgg.champion.domain.champions;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface ChampionsRepository extends JpaRepository<Champion, Integer> {
    @Query("select c from Champion c")
    List<Champion> findAll();
}
