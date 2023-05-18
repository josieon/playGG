package com.playGG.master.domain.League;

import lombok.*;

@Getter
@Setter
public class SummonerDTO {
    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private int summonerLevel;


}