package com.playGG.master.domain.Statistic;

import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
public class Spells {
    List<String> spell;
    float pickRate;
    float winRate;
    int picCount;
}
