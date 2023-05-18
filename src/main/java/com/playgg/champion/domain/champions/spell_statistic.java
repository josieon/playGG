package com.playgg.champion.domain.champions;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class spell_statistic {
    @EmbeddedId
    private SpellPK spellPK;
    private int wins;
    private int choices;
}
