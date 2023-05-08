package com.playdata.springbootproject.fow.InGame;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CurrentGameParticipant {

    private long championId;
    private Perks perks;
    private long profileIconId;
    private boolean bot;
    private long teamId;
    private String summonerName;
    private String summonerId;
    private long spell1Id;
    private long spell2Id;
    private List<GameCustomizationObject> gameCustomizationObjects;
}
