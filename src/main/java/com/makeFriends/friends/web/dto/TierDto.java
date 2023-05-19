package com.makeFriends.friends.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TierDto {
    private String summonerName;
    private String tier;
    private String rank;


}
