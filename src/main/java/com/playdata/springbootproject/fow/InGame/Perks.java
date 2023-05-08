package com.playdata.springbootproject.fow.InGame;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Perks {
    private List<Long> perkIds;
    private long perkStyle;
    private long perkSubStyle;
}
