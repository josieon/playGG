package com.playGG.master.domain.Statistic;

import jakarta.persistence.*;
import lombok.*;

import java.io.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CounterPK implements Serializable {
    private int championId;
    private int counterId;
}
