package com.playdata.springbootproject.fow.service;

import com.playdata.springbootproject.fow.DTO.InGame.CurrentGameInfo;
import com.playdata.springbootproject.fow.DTO.league.LeagueEntryDTO;
import com.playdata.springbootproject.fow.DTO.Match.MatchDto;
import com.playdata.springbootproject.fow.DTO.SummonerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiotApiService {
    @Value("${apiKey}")
    private String API_KEY;

    @Value("${apiKey2}")
    private String API_KEY2;

    //다른서버에 요청을 해주는 역할을 가진 객체
    RestTemplate restTemplate = new RestTemplate();

    public SummonerDto getSummoner(String name) {
        //서머너
        ResponseEntity<SummonerDto> respSummoner = restTemplate.getForEntity(
                "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ name +"?api_key=" + API_KEY,
                SummonerDto.class);
         return respSummoner.getBody();
    }

    public LeagueEntryDTO[] getLeague(SummonerDto summonerDto) {
        //리그
        ResponseEntity<LeagueEntryDTO[]> respLeague = restTemplate.getForEntity(
                "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+ summonerDto.getId() +"?api_key=" + API_KEY,
                LeagueEntryDTO[].class);
        return respLeague.getBody();
    }

    public CurrentGameInfo getInGame(SummonerDto summonerDto) throws Exception {
        ResponseEntity<CurrentGameInfo> resp_InGame = restTemplate.getForEntity(
                "https://kr.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/" + summonerDto.getId() + "?api_key=" + API_KEY,
                CurrentGameInfo.class);
        CurrentGameInfo inGameDto = resp_InGame.getBody();
        if (inGameDto == null) {
            throw new Exception("API Error: InGame is null");
        }
        return inGameDto;
    }


        public List<String> getMatchList (SummonerDto summonerDto){
        //매치 리스트
        ResponseEntity<ArrayList> respMatchList = restTemplate.getForEntity(
                "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summonerDto.getPuuid() + "/ids?start=0&count=10&api_key=" + API_KEY,
                ArrayList.class);
        return respMatchList.getBody();
    }


    public MatchDto getMatch(String match) {
        //매치
        ResponseEntity<MatchDto> respMatch = restTemplate.getForEntity(
                "https://asia.api.riotgames.com/lol/match/v5/matches/" + match + "?api_key=" + API_KEY,
                MatchDto.class);
        System.out.println(respMatch.getBody());
        return respMatch.getBody();
    }


}