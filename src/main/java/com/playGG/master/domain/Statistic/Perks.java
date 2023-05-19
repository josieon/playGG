package com.playGG.master.domain.Statistic;

import lombok.*;

import java.util.*;
import java.util.stream.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Perks {
    List<Perk> perk;
    float pickRate;
    float winRate;
//    public Perks(perk_statistic e, int gameCount) {
//        String[] p = e.getPerkPK().getPerks().split(",");
//        this.perk = Arrays.stream(p).map(Integer::parseInt).map(Perk::new).collect(Collectors.toList());
//        this.pickRate = (float)Math.round(10000 * e.getChoices() / gameCount) / 100;
//        this.winRate = (float)Math.round(10000 * e.getWins() / e.getChoices()) / 100;
//    }
}
