package com.playdata.springbootproject.fow.DTO.Match;

import lombok.Getter;

import java.util.List;

@Getter
public class TeamDto {
    List<BanDto> bans;
    ObjectivesDto objectives;
    int teamId;
    boolean win;
}
