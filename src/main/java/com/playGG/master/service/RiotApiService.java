package com.playGG.master.service;

import com.playGG.master.domain.InGame.*;
import com.playGG.master.domain.League.*;
import com.playGG.master.domain.Match.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import java.util.*;

@Service
public class RiotApiService {
    @Value("${apiKey}")
    private String API_KEY;

//    @Value("${apiKey2}")
//    private String API_KEY2;

    //다른서버에 요청을 해주는 역할을 가진 객체
    RestTemplate restTemplate = new RestTemplate();

    public SummonerDTO getSummoner(String name) {
        //서머너
        ResponseEntity<SummonerDTO> respSummoner = restTemplate.getForEntity(
                "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ name +"?api_key=" + API_KEY,
                SummonerDTO.class);
        return respSummoner.getBody();
    }

    public LeagueEntryDTO[] getLeague(SummonerDTO summonerDto) {
        //리그
        ResponseEntity<LeagueEntryDTO[]> respLeague = restTemplate.getForEntity(
                "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+ summonerDto.getId() +"?api_key=" + API_KEY,
                LeagueEntryDTO[].class);
        return respLeague.getBody();
    }

    public CurrentGameInfo getInGame(SummonerDTO summonerDto) throws Exception {
        ResponseEntity<CurrentGameInfo> resp_InGame = restTemplate.getForEntity(
                "https://kr.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/" + summonerDto.getId() + "?api_key=" + API_KEY,
                CurrentGameInfo.class);
        CurrentGameInfo inGameDto = resp_InGame.getBody();
        if (inGameDto == null) {
            throw new Exception("API Error: InGame is null");
        }
        return inGameDto;
    }


    public List<String> getMatchList (SummonerDTO summonerDto){
        //매치 리스트
        ResponseEntity<ArrayList> respMatchList = restTemplate.getForEntity(
                "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summonerDto.getPuuid() + "/ids?start=0&count=10&api_key=" + API_KEY,
                ArrayList.class);
        return respMatchList.getBody();
    }


    public matchDTO getMatch(String match) {
        //매치
        ResponseEntity<matchDTO> respMatch = restTemplate.getForEntity(
                "https://asia.api.riotgames.com/lol/match/v5/matches/" + match + "?api_key=" + API_KEY,
                matchDTO.class);
        return respMatch.getBody();
    }


}