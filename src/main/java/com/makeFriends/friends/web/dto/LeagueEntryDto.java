package com.makeFriends.friends.web.dto;

import lombok.Getter;

@Getter
public class LeagueEntryDto {
    private String leagueId;
    private String summonerId;
    private String summonerName;
    private String queueType;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean hotstreak;
    private boolean veteran;
    private boolean freshBlood;
    private boolean inactive;


}
