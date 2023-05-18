package com.playgg.champion.domain.champions;

import lombok.*;

@Getter
@Setter
@Builder
public class Items {
    int itemId;
    float pickRate;
    float winRate;
}
