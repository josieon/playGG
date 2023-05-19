package com.playGG.master.domain.Statistic;

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
