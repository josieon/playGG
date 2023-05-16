package com.playgg.champion.web.dto;

import com.playgg.champion.domain.champions.*;
import lombok.*;

@Getter
public class ChampionResponseDto {
    private Integer championId;
    private String champName;
    private Integer wins;
    private Integer choices;
    private Integer bans;
    private Float winRate;
//    private List<Perks> perks;
//    private List<Skills> skills;
//    private List<Builds> builds;
//    private List<Spells> spells;
//    private List<CounterDto> counters_hard;
//    private List<CounterDto> counters_easy;
//    private List<Tips> tips;

    public ChampionResponseDto(Champion champ) {
        this.championId = champ.getChampionId();
        this.champName = champ.getChampName();
        this.wins = champ.getWins();
        this.choices = champ.getChoices();
        this.bans = champ.getBans();
        this.winRate = (float)wins/choices*100;
    }
}
