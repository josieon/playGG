package com.playdata.springbootproject.fow;
import com.playdata.springbootproject.fow.InGame.BannedChampion;
import com.playdata.springbootproject.fow.InGame.CurrentGameParticipant;
import com.playdata.springbootproject.fow.InGame.Observer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InGameDto {
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

}
