package com.playGG.master.domain.InGame;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class CurrentGameInfo {
    private long gameId;
    private String gameType;
    private long gameStartTime;
    private long mapId;
    private long gameLength;
    private String platformId;
    private String gameMode;

    private List<BannedChampion> bannedChampions;
    private long gameQueueConfigId;
    private Observer Observers;
    private List<CurrentGameParticipant> participants;

    private String currentGameTime;
}
