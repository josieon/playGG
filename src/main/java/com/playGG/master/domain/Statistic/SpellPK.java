package com.playGG.master.domain.Statistic;

import jakarta.persistence.*;
import lombok.*;

import java.io.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class SpellPK implements Serializable {
    private int championId;
    private int summoner1;
    private int summoner2;
}
