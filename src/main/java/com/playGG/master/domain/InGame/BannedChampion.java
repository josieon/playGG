package com.playGG.master.domain.InGame;

import lombok.*;

@Getter
@Setter
public class BannedChampion {
    private int pickTurn;
    private long championId;
    private long teamId;
}