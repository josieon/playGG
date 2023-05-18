package com.playgg.champion.domain.champions;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CounterRepository extends JpaRepository<counter_statistic, CounterPK> {
    @Query("SELECT c FROM counter_statistic c WHERE c.counterPK.championId = :id ORDER BY c.choices desc limit 20")
    List<counter_statistic> findAllById(@Param("id") int id);
}
