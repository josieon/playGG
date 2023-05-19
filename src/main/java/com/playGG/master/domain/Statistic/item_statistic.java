package com.playGG.master.domain.Statistic;

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
