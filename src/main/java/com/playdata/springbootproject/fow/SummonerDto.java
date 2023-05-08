package com.playdata.springbootproject.fow;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummonerDto {
    private String id;
    private String accountId;
    private String puuid;
    private String name;
//    private int profileIconId;
    private long revisionDate;
    private int summonerLevel;


}
