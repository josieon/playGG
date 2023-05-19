package com.playdata.springbootproject.fow.DTO;

import com.playdata.springbootproject.fow.DTO.InGame.GameCustomizationObject;
import com.playdata.springbootproject.fow.DTO.InGame.Perks;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class InGameContainer {


    private long championId;
    private long spell1Id;
    private long spell2Id;
    private String summonerName;

    private long perkIds;
    private long perkSubStyle;

    private String tier;
    private String rank;
    List<Long> inGameChampIds = new ArrayList<>();

    String inGameSumName = null;
    List<String> inGameSumNames = new ArrayList<>();

    long inGameSpell1 =  Long.parseLong(null);
    List<Long> inGameSpell1s = new ArrayList<>();
    long inGameSpell2 =  Long.parseLong(null);
    List<Long> inGameSpell2s = new ArrayList<>();

    long inGameSubStyle = Long.parseLong(null);
    List<Long> inGameSubStyles = new ArrayList<>();

    long inGameMainPerk = Long.parseLong(null);
    List<Long> inGameMainPerks = new ArrayList<>();

}
