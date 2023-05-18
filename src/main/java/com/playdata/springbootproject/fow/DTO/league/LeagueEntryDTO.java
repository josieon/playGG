package com.playdata.springbootproject.fow.DTO.league;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeagueEntryDTO {
    private String leagueId;
    private String summonerId;
    private String summonerName;
    private String queueType;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean hotStreak;
    private boolean veteran;
    private boolean freshBlood;
    private boolean inactive;
    private MiniSeriesDTO miniSeries;

    private int total;

    private int winsPercent;

}