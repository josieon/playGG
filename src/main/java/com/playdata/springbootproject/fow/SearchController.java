package com.playdata.springbootproject.fow;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class SearchController {
    @Value("${apiKey}")
    private String API_KEY;

    // PathVariable이 뭔지, RequestParam이 뭔지
    // RequestParma: /summoners/kr?name=타잔
    // PathVariable: /summoners/kr/타잔
    @GetMapping("/summoners/kr/{name}")
    public String search(@PathVariable String name) {
        //name을 가지고 Riot 서버에 요청

        //다른서버에 요청을 해주는 역할을 가진 객체
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getForEntity()
//        restTemplate.getForObject()

        ResponseEntity<SummonerDto> resp_Summoner = restTemplate.getForEntity(
                "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ name +"?api_key=" + API_KEY,
                SummonerDto.class);
        SummonerDto summoner = resp_Summoner.getBody();

        ResponseEntity<LeagueDto[]> resp_League = restTemplate.getForEntity(
                "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+ summoner.getId() +"?api_key=" + API_KEY,
                LeagueDto[].class);
        LeagueDto[] league = resp_League.getBody();

        //인게임 정보
        ResponseEntity<InGameDto> resp_InGame = restTemplate.getForEntity(
                    "https://kr.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/" + summoner.getId() + "?api_key=" + API_KEY,
                    InGameDto.class);
        InGameDto InGame = resp_InGame.getBody();



        String ParticipantName1 = InGame.getParticipants().get(0).getSummonerName();
        String ParticipantName2 = InGame.getParticipants().get(1).getSummonerName();


        long timeSeocnds = InGame.getGameLength();
        int  timeMinutes = (int) (timeSeocnds/ 60);
        timeSeocnds %= 60;

        System.out.println(name);
        System.out.println("레벨: "+ summoner.getSummonerLevel());
        System.out.println("리그: " + league[0].getQueueType());
        System.out.println("등급: " + league[0].getTier() + " " + league[0].getRank());
        System.out.println("리그 포인트: " + league[0].getLeaguePoints());
        System.out.println(league[0].getWins()+league[0].getLosses() + "전 " + league[0].getWins() + "승 " + league[0].getLosses() +"패");
        System.out.println(summoner.getId());
        System.out.println(summoner.getPuuid());


        String result = name + "<br>";
        result += "레벨: " + summoner.getSummonerLevel() + "<br>";
        result += "리그: " + league[0].getQueueType() + "<br>";
        result += "등급: " + league[0].getTier() + " " + league[0].getRank() + "<br>";
        result += "리그 포인트: " + league[0].getLeaguePoints() + "<br>";
        result += league[0].getWins() + league[0].getLosses() + "전 " + league[0].getWins() + "승 " + league[0].getLosses() +"패" + "<br>";
        result += "<br>";
        result += "게임 시간 : " + timeMinutes + "분 " + timeSeocnds + "초" + "<br>";
        result += "참가자1 : " + ParticipantName1 + "<br>";
        result += "참가자2 : " + ParticipantName2;


        return result;
    }
}
