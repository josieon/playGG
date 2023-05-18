package com.playGG.master.domain.Statistic;


import jakarta.persistence.*;
import lombok.*;

import java.io.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PerkPK implements Serializable {
    private int championId;
    private String perks;
}
