package com.playGG.master.domain.Statistic;

import lombok.*;

@Getter
@Setter
@Builder
public class CounterDto {
    int championId;
    String imageURL;
    float winRate;
}
