package com.playGG.master.domain.Statistic;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface ItemRepository extends JpaRepository<item_statistic, ItemPK> {
    @Query("SELECT i FROM item_statistic i WHERE i.itemPK.championId = :id ORDER BY i.choices desc limit 20")
    List<item_statistic> findAllById(@Param("id") int id);
}
