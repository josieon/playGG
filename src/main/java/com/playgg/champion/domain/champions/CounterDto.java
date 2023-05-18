package com.playgg.champion.domain.champions;

import lombok.*;

@Getter
@Setter
@Builder
public class CounterDto {
    int championId;
    float winRate;
}
