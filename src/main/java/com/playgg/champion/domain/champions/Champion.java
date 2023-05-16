package com.playgg.champion.domain.champions;

import jakarta.persistence.*;
import lombok.*;

import java.io.*;

@Getter
@NoArgsConstructor
@Entity
public class Champion implements Serializable {
    @Id
    private Integer championId;
    @Column(length = 30, nullable = false)
    private String champName;
    private Integer wins;
    private Integer choices;
    private Integer bans;

    @Builder
    public Champion(Integer championId, String champName, Integer wins, Integer choices, Integer bans) {
        this.championId = championId;
        this.champName = champName;
        this.wins = wins;
        this.choices = choices;
        this.bans = bans;
    }
}
