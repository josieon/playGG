package com.playdata.springbootproject.fow.DTO.Match;

import lombok.Getter;

import java.util.List;

@Getter
public class InfoDto {
    long gameCreation;
    long gameDuration;
    long gameEndTimestamp;
    long gameId;
    String gameMode;
    String gameName;
    long gameStartTimestamp;
    String gameType;
    String gameVersion;
    int mapId;
    List<ParticipantDto> participants;
    String platformId;
    int queueId;
    List<TeamDto> teams;
    String tournamentCode;
}
