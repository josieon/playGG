package com.playdata.springbootproject.fow;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeagueDto {
    private String leagueId;
    private String queueType; //RANKED_SOLO_5x5
    private String tier;      //GRANDMASTER
    private String rank;      // 1
    private String summonerId;   //암호화된
    private String summonerName;  //hide on bush
    private int leaguePoints;   //696
    private int wins;
    private int losses;
    private boolean veteran;
    private boolean inactive;
    private boolean freshBlood;
    private boolean hotStreak;
}