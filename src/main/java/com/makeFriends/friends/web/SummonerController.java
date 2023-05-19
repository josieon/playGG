package com.makeFriends.friends.web;

import com.makeFriends.friends.service.RiotAPIService;
import com.makeFriends.friends.web.dto.LeagueEntryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summoner")
public class SummonerController {
    @Autowired
    private RiotAPIService riotAPIService;

    private static final String API_KEY ="apikey";

    @GetMapping("/{summoner}/id")
    public String getSummonerIdByName(@PathVariable String summonerName) {
        try {
            LeagueEntryDto leagueEntry = riotAPIService.getSummonerIdByName(summonerName);

            if(leagueEntry != null){
                String summonerId = leagueEntry.getSummonerId();
                String tier = leagueEntry.getTier();
                String rank = leagueEntry.getRank();

                return summonerId;
            } else {
                throw new Exception("Summoner not found");
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}





