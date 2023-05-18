package com.playgg.champion.domain.champions;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
public class perk_statistic {
    @EmbeddedId
    private PerkPK perkPK;
    private int wins;
    private int choices;

    @Builder
    public perk_statistic(int championId, String perks, int wins, int choices) {
        this.perkPK = new PerkPK(championId, perks);
        this.wins = wins;
        this.choices = choices;
    }
}
