package com.playgg.champion.web.dto;

import com.playgg.champion.domain.champions.*;
import lombok.*;

import java.util.*;

@Builder
@Getter
public class ChampionResponseDto {
    private champion_statistic statistic;
    private Float winRate;
    private Float pickRate;
    private Float banRate;
    private List<Perks> perks;
//    private List<Skills> skills;
//    private List<Builds> builds;
    private List<Spells> spells;
    private List<Items> items;
    private List<CounterDto> counters_hard;
    private List<CounterDto> counters_easy;
//    private List<Tips> tips;
}
