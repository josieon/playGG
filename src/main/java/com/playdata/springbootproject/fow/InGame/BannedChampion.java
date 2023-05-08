package com.playdata.springbootproject.fow.InGame;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BannedChampion {
    private int pickTurn;
    private long championId;
    private long teamId;
}
