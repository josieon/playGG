package com.makeFriends.friends.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makeFriends.friends.web.dto.LeagueEntryDto;
import com.makeFriends.friends.web.dto.SummonerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RiotAPIService {
    @Value("${apiKey}")
    private String API_KEY;


    //다른서버에 요청을 해주는 역할을 가진 객체
    RestTemplate restTemplate = new RestTemplate();

    public SummonerDto getSummoner(String name) {
        //서머너
        ResponseEntity<SummonerDto> respSummoner = restTemplate.getForEntity(
                "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ name +"?api_key=" + API_KEY,
                SummonerDto.class);
        return respSummoner.getBody();
    }


    public LeagueEntryDto[] getLeague(SummonerDto summonerDto) {
        //리그
        ResponseEntity<LeagueEntryDto[]> respLeague = restTemplate.getForEntity(
                "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+ summonerDto.getId() +"?api_key=" + API_KEY,
                LeagueEntryDto[].class);
        return respLeague.getBody();
    }

    public LeagueEntryDto getSummonerIdByName(String summonerName)throws Exception{
        String urlString = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+summonerName+"?api_key="+API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if(responseCode == 200){
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            reader.close();

            ObjectMapper objectMapper = new ObjectMapper();
            LeagueEntryDto[] leagueEntries = objectMapper.readValue(response.toString(), LeagueEntryDto[].class);

            if(leagueEntries.length >0) {
                return leagueEntries[0];
            }else {
                return null;
            }
        }else {
            throw new Exception("Failed to retrieve summoner league info");
        }


    }


}
