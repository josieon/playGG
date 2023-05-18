package com.playdata.springbootproject.fow.DTO.Match;

import lombok.Getter;

import java.util.List;
@Getter
public class MetadataDto {
    String dataVersion;
    String matchId;
    List<String> participants;
}
