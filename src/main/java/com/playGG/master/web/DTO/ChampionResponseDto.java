package com.playGG.master.web.DTO;
import com.playGG.master.domain.Statistic.*;
import lombok.*;

import java.util.*;

@Builder
@Getter
public class ChampionResponseDto {
    private champion_statistic statistic;
    private String profileImage;
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
