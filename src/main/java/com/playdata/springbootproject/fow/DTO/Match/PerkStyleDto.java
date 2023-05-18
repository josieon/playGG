package com.playdata.springbootproject.fow.DTO.Match;

import lombok.Getter;

import java.util.List;

@Getter
public class PerkStyleDto {
    String description;
    List<PerkStyleSelectionDto> selections;
    int style;
}
