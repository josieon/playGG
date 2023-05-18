package com.playgg.champion.domain.champions;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class item_statistic {
    @EmbeddedId
    private ItemPK itemPK;
    private int wins;
    private int choices;
}
