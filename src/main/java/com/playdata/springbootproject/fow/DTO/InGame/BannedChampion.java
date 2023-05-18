package com.playdata.springbootproject.fow.DTO.InGame;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BannedChampion {
    private int pickTurn;
    private long championId;
    private long teamId;
}
