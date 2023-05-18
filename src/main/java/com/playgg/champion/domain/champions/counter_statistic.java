package com.playgg.champion.domain.champions;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class counter_statistic {
    @EmbeddedId
    private CounterPK counterPK;
    private int wins;
    private int choices;
}
