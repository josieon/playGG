package com.playGG.master.web.DTO;
import com.playGG.master.domain.Statistic.*;
import lombok.*;

@Getter
public class ChampsListResponseDto {
    private Integer championId;
    private String championName;
    private Integer wins;
    private Integer choices;
    private Integer bans;
    private Float winRate;
    private Float pickRate;
    private Float banRate;

    public void setPickRate(Float pickRate) {
        this.pickRate = pickRate;
    }

    public void setBanRate(Float banRate) {
        this.banRate = banRate;
    }

    public ChampsListResponseDto(champion_statistic champ, Float pickRate, Float banRate){
        this.championId = champ.getChampionId();
        this.championName = champ.getChampionName();
        this.wins = champ.getWins();
        this.choices = champ.getChoices();
        this.bans = champ.getBans();
        this.winRate = (float)Math.round(10000 * wins/choices)/100;
        this.pickRate = (float)Math.round(10000*pickRate)/100;
        this.banRate = (float)Math.round(10000*banRate)/100;
    }
}
