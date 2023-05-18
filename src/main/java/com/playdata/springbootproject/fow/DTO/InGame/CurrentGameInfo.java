package com.playdata.springbootproject.fow.DTO.InGame;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
