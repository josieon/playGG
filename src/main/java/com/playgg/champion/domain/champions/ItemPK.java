package com.playgg.champion.domain.champions;

import jakarta.persistence.*;
import lombok.*;

import java.io.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ItemPK implements Serializable {
    private int championId;
    private int itemId;
}
