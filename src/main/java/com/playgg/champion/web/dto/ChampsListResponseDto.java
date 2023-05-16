package com.playgg.champion.web.dto;

import com.playgg.champion.domain.champions.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
public class ChampsListResponseDto {
    private Integer championId;
    private String champName;
    private Integer wins;
    private Integer choices;
    private Integer bans;
    private Float winRate;

    public ChampsListResponseDto(Champion champ){
        this.championId = champ.getChampionId();
        this.champName = champ.getChampName();
        this.wins = champ.getWins();
        this.choices = champ.getChoices();
        this.bans = champ.getBans();
        this.winRate = (float)wins/choices*100;
    }
}
