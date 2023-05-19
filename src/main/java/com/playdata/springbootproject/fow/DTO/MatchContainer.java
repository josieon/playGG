package com.playdata.springbootproject.fow.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchContainer {
    private String championName;
    private int championLevel;
    private int kills;
    private int deaths;
    private int assists;
    private String kda;

    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;

    private String mainPerk;
    private String subStyle;
    private String queueType2;
    private String timeAgo;
    private String isWins;
    private String isWins2;
    private int gameMinutes;
    private int gameSeconds;
    private int visionWard;
    private int totalCs;
    private double csPerMin;
    private int killsPartRate;

    private String topUser1;
    private String jungleUser1;
    private String midUser1;
    private String adUser1;
    private String supUser1;
    private String topUser2;
    private String jungleUser2;
    private String midUser2;
    private String adUser2;
    private String supUser2;

    private String topChampName1;
    private String jungleChampName1;
    private String midChampName1;
    private String adChampName1;
    private String supChampName1;
    private String topChampName2;
    private String jungleChampName2;
    private String midChampName2;
    private String adChampName2;
    private String supChampName2;

    private String summoner1Id;
    private String summoner2Id;
}
