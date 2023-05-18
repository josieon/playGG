package com.playdata.springbootproject.fow.DTO.Match;

import lombok.Getter;

import java.util.List;

@Getter
public class PerksDto {
    PerkStatsDto statPerks;
    List<PerkStyleDto> styles;
}
