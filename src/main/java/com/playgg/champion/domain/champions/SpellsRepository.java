package com.playgg.champion.domain.champions;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface SpellsRepository extends JpaRepository<spell_statistic, SpellPK> {
    @Query("SELECT s FROM spell_statistic s WHERE s.spellPK.championId = :id ORDER BY s.choices desc limit 2")
    List<spell_statistic> findAllBy(@Param("id") int id);
}
