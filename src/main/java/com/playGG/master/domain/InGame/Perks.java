package com.playGG.master.domain.InGame;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class Perks {
    private List<Long> perkIds;
    private long perkStyle;
    private long perkSubStyle;
}