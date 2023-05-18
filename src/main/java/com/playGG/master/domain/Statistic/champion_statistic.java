package com.playGG.master.domain.Statistic;
import jakarta.persistence.*;
import lombok.*;

import java.io.*;

@Getter
@NoArgsConstructor
@Entity
public class champion_statistic implements Serializable {
    @Id
    private Integer championId;
    @Column(length = 30, nullable = false)
    private String championName;
    private Integer wins;
    private Integer choices;
    private Integer bans;

    @Builder
    public champion_statistic(Integer championId, String championName, Integer wins, Integer choices, Integer bans) {
        this.championId = championId;
        this.championName = championName;
        this.wins = wins;
        this.choices = choices;
        this.bans = bans;
    }
}
