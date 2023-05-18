package com.playGG.master.web;

import com.playGG.master.domain.InGame.*;
import com.playGG.master.domain.League.*;
import com.playGG.master.domain.Match.*;
import com.playGG.master.service.*;
import com.playGG.master.web.DTO.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.web.servlet.*;

import java.time.*;
import java.util.*;

@RequiredArgsConstructor
@RestController
@Controller
public class summonerController {
    private final RiotApiService riotApiService;

    @Value("${apiKey}")
    private String API_KEY;

    // PathVariable이 뭔지, RequestParam이 뭔지
    // RequestParma: /summoners/kr?name=타잔
    // PathVariable: /summoners/kr/타잔
    @GetMapping("/summoners/kr")
    public ModelAndView search(@RequestParam String name) {
        //name을 가지고 Riot 서버에 요청

        //다른서버에 요청을 해주는 역할을 가진 객체
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getForEntity()
//        restTemplate.getForObject()
        ModelAndView modelAndView = new ModelAndView();

        SummonerDTO summoner = null;


        try {
            summoner = riotApiService.getSummoner(name);
            // 성공적으로 서머너 정보를 받아왔을 때의 처리
            modelAndView.addObject("yesSummoner", summoner);

        } catch (HttpClientErrorException.NotFound e) {
            // 404 에러가 발생한 경우의 예외 처리 로직
            modelAndView.addObject("noSummoner", "존재하지 않는 소환사명입니다.");
        } catch (RuntimeException e) {
            // 기타 예외 처리 로직
        }


        LeagueEntryDTO[] league = null;
        try {
            league = riotApiService.getLeague(summoner);
            // 성공적으로 서머너 정보를 받아왔을 때의 처리
        } catch (RuntimeException e) {
            // 기타 예외 처리 로직
        }

        CurrentGameInfo inGame = null;
        String errorMessage = null;


        CurrentGameParticipant currentGameParticipant = new CurrentGameParticipant();
        BannedChampion bannedChampion = new BannedChampion();
        CurrentGameInfo currentGameInfo = new CurrentGameInfo();
        Perks perks = new Perks();

        LeagueEntryDTO[] InGameLeague = null;
        List<LeagueEntryDTO[]> InGameLeaues = null;
        //인게임 정보
        try {
            inGame = riotApiService.getInGame(summoner);
            // InGame 정보를 이용한 로직 구현
            for (int i =0; i<10; i++) {
                //게임 참가자들의 리그 정보;
                InGameLeague =  riotApiService.getLeague(riotApiService.getSummoner(inGame.getParticipants().get(i).getSummonerName()));
                int soloRankIndex = InGameLeague.length;
                currentGameParticipant.setTier(InGameLeague[soloRankIndex].getTier());
                currentGameParticipant.setRank(InGameLeague[soloRankIndex].getRank());
            }
            modelAndView.addObject("inGame", inGame);
        } catch(HttpClientErrorException ex){
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                errorMessage = "'" + summoner.getName() + "'" + "님은 현재 게임중이 아닙니다.";
                modelAndView.addObject("noCurrentGame", "noCurrentGame");
                modelAndView.addObject("noCurrentGameSummoner", summoner);
            }
            // 다른 예외 상황 처리
        } catch(Exception ex){
            // 예상치 못한 예외 상황 처리
        }

        String currentGameTime = null;
        try {
            if (errorMessage == null) {
                long timeSeocnds = inGame.getGameLength();
                int timeMinutes = (int) (timeSeocnds / 60);
                timeSeocnds %= 60;

                currentGameTime += "게임 시간 : " + timeMinutes + "분 " + timeSeocnds + "초";
            } else {
                currentGameTime += errorMessage;
            }
        }catch (RuntimeException e) {
        }

        //리그
        try {
            if (league.length == 0) {
                modelAndView.addObject("unranked", "UNRANKED");
            } else if (league.length >= 1) {
                List<LeagueEntryDTO> leagueEntryDTOS = new ArrayList<>();
                for (int i = 0; i < league.length; i++) {
                    LeagueEntryDTO dto = LeagueEntryDTO.builder()
                            .queueType(league[i].getQueueType())
                            .tier(league[i].getTier())
                            .rank(league[i].getRank())
                            .leaguePoints(league[i].getLeaguePoints())
                            .wins(league[i].getWins())
                            .losses(league[i].getLosses())
                            .total(league[i].getWins() + league[i].getLosses())
                            .winsPercent((int) Math.round((double) league[i].getWins() / (league[i].getWins() + league[i].getLosses()) * 100))
                            .build();
                    leagueEntryDTOS.add(dto);
                }
                modelAndView.addObject("leagueEntryDTOS", leagueEntryDTOS);
            }
        }catch (RuntimeException e) {

        }


        List<String> matchList = null;
        try{
            matchList = riotApiService.getMatchList(summoner);
        }catch (RuntimeException e) {

        }

        matchDTO match = null;
        List<matchDTO> matches = new ArrayList<>();

        try {
            for (int i = 0; i < 5; i++) {
                matchDTO tempMatch = riotApiService.getMatch(matchList.get(i));
                matches.add(tempMatch);
            }
        }catch (RuntimeException e){

        }

//        ParticipantDto participantStart = null;
        List<participantDTO> participantStart = new ArrayList<>();

        try {
            //match 데이터 안에서 검색한 닉넴 찾기
            for (int i = 0; i < 5; i++) {
                match = matches.get(i);
                for (int j = 0; j < 10; j++) {
                    String matchPuuid = match.getInfo().getParticipants().get(j).getPuuid();
                    if (summoner.getPuuid().equals(matchPuuid)) {
                        participantDTO tempParticipant = match.getInfo().getParticipants().get(j);
                        participantStart.add(tempParticipant);
                    }
                }
            }
        }catch (RuntimeException e){

        }

        List<MatchContainer> matchContainers = new ArrayList<>();

        try {
            //MatchContainer
            String Name = summoner.getName();
            for (int i = 0; i < 5; i++) {
                MatchContainer matchContainer = new MatchContainer();

                String championName = participantStart.get(i).getChampionName();
                int championLevel = participantStart.get(i).getChampLevel();
                int kills = participantStart.get(i).getKills();
                int deaths = participantStart.get(i).getDeaths();
                int assists = participantStart.get(i).getAssists();
                double tempKda = (double) (kills + assists) / deaths;
                String kda = String.format("%.2f", tempKda);

                //아이템 이미지
                int item0 = participantStart.get(i).getItem0();
                int item1 = participantStart.get(i).getItem1();
                int item2 = participantStart.get(i).getItem2();
                int item3 = participantStart.get(i).getItem3();
                int item4 = participantStart.get(i).getItem4();
                int item5 = participantStart.get(i).getItem5();
                int item6 = participantStart.get(i).getItem6();

                //소환사 주문 아이디
                int summoner1Id = participantStart.get(i).getSummoner1Id();
                int summoner2Id = participantStart.get(i).getSummoner2Id();

                //주 룬의 대표 특성 아이디
                int mainPerk = participantStart.get(i).getPerks().getStyles().get(0).getSelections().get(0).getPerk();
                //보조 룬 스타일 아이디
                int subStyle = participantStart.get(i).getPerks().getStyles().get(1).getStyle();

                RuneMapper runeMapper = new RuneMapper();
                String mainPerkURL = runeMapper.getRuneCode(mainPerk);
                String subStyleURL = runeMapper.getRuneCode(subStyle);

                String mapSummoner1Id = runeMapper.getSpellCode(summoner1Id);
                String mapSummoner2Id = runeMapper.getSpellCode(summoner2Id);


                /// 게임 모드 구하기
                int queueId = matches.get(i).getInfo().getQueueId();
                String queueType2;
                if (queueId == 420) {
                    queueType2 = "솔랭";
                } else if (queueId == 430) {
                    queueType2 = "일반";
                } else if (queueId == 440) {
                    queueType2 = "자유랭크";
                } else if (queueId == 450) {
                    queueType2 = "무작위 총력전";
                } else {
                    // 매핑되는 값이 없는 경우에 대한 처s리
                    queueType2 = "알 수 없음";
                }


                /// 며칠 전 구하기
                Instant now = Instant.now();

                // 주어진 타임스탬프
                long timestamp = matches.get(i).getInfo().getGameEndTimestamp();

                // 주어진 타임스탬프를 Instant 객체로 변환
                Instant then = Instant.ofEpochMilli(timestamp);

                // 현재 시각과의 차이 구하기
                Duration duration = Duration.between(then, now);
                long seconds = duration.getSeconds();
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;

                String timeAgo;
                if (seconds < 60) {
                    timeAgo = "방금 전";
                } else if (minutes < 60) {
                    timeAgo = minutes + "분 전";
                } else if (hours < 24) {
                    timeAgo = hours + "시간 전";
                } else {
                    timeAgo = days + "일 전";
                }


                ///승패 구하기
                String isWins;
                if (participantStart.get(i).isWin() == true) {
                    isWins = "승리";
                } else {
                    isWins = "패배";
                }


                ///게임시간 구하기

                long gameTime = matches.get(i).getInfo().getGameDuration();

                int gameMinutes = (int) gameTime / 60;
                int gameSeconds = (int) gameTime % 60;


                //제어와드
                int visionWard = participantStart.get(i).getVisionWardsBoughtInGame();
                //총cs, 분당cs
                int totalCs = participantStart.get(i).getTotalMinionsKilled() + participantStart.get(i).getNeutralMinionKilled();
                double temCsPerMin = (double) totalCs / gameTime * 60;
                double csPerMin = (double) Math.round(temCsPerMin * 10) / 10;

                //킬관여율
                int totalKillsTeam100 = 0;
                int totalKillsTeam200 = 0;
                int killsPartRate = 0;

                // 각 참여자의 킬수를 구하여 teamId 값에 따라 합해서 양팀의 총 킬수를 각각 구한다.
                for (int j = 0; j < 10; j++) {
                    int eachkills = matches.get(i).getInfo().getParticipants().get(j).getKills();
                    int teamId = matches.get(i).getInfo().getParticipants().get(j).getTeamId();

                    if (teamId == 100) {
                        totalKillsTeam100 += eachkills;
                    } else if (teamId == 200) {
                        totalKillsTeam200 += eachkills;
                    }
                }


                if (participantStart.get(i).getKills() + participantStart.get(i).getAssists() == 0) {
                    killsPartRate = 0;
                } else if (participantStart.get(i).getTeamId() == 100) {
                    killsPartRate = (int) Math.round((double) (participantStart.get(i).getKills() + participantStart.get(i).getAssists()) / totalKillsTeam100 * 100);
                } else {
                    killsPartRate = (int) Math.round((double) (participantStart.get(i).getKills() + participantStart.get(i).getAssists()) / totalKillsTeam200 * 100);
                }

                String topUser1 = null;
                String jungleUser1 = null;
                String midUser1 = null;
                String adUser1 = null;
                String supUser1 = null;
                String topUser2 = null;
                String jungleUser2 = null;
                String midUser2 = null;
                String adUser2 = null;
                String supUser2 = null;

                String topChampName1 = null;
                String jungleChampName1 = null;
                String midChampName1 = null;
                String adChampName1 = null;
                String supChampName1 = null;
                String topChampName2 = null;
                String jungleChampName2 = null;
                String midChampName2 = null;
                String adChampName2 = null;
                String supChampName2 = null;

                //매치 참가자 목록
                for (int k = 0; k < 10; k++) {
                    String position = matches.get(i).getInfo().getParticipants().get(k).getIndividualPosition();
                    int teamId = matches.get(i).getInfo().getParticipants().get(k).getTeamId();

                    if (teamId == 100 && (position.equals("TOP"))) {
                        topUser1 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                        topChampName1 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                    } else if (teamId == 100 && (position.equals("JUNGLE"))) {
                        jungleUser1 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                        jungleChampName1 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                    } else if (teamId == 100 && (position.equals("MIDDLE"))) {
                        midUser1 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                        midChampName1 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                    } else if (teamId == 100 && (position.equals("BOTTOM"))) {
                        adUser1 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                        adChampName1 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                    } else if (teamId == 100 && (position.equals("UTILITY"))) {
                        supUser1 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                        supChampName1 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                    } else if (teamId == 200 && (position.equals("TOP"))) {
                        topUser2 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                        topChampName2 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                    } else if (teamId == 200 && (position.equals("JUNGLE"))) {
                        jungleUser2 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                        jungleChampName2 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                    } else if (teamId == 200 && (position.equals("MIDDLE"))) {
                        midUser2 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                        midChampName2 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                    } else if (teamId == 200 && (position.equals("BOTTOM"))) {
                        adUser2 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                        adChampName2 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                    } else if (teamId == 200 && (position.equals("UTILITY"))) {
                        supUser2 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                        supChampName2 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                    } else {
                        if (k == 0) {
                            topUser1 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                            topChampName1 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                        }
                        if (k == 1) {
                            jungleUser1 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                            jungleChampName1 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                        }
                        if (k == 2) {
                            midUser1 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                            midChampName1 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                        }
                        if (k == 3) {
                            adUser1 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                            adChampName1 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                        }
                        if (k == 4) {
                            supUser1 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                            supChampName1 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                        }
                        if (k == 5) {
                            topUser2 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                            topChampName2 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                        }
                        if (k == 6) {
                            jungleUser2 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                            jungleChampName2 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                        }
                        if (k == 7) {
                            midUser2 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                            midChampName2 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                        }
                        if (k == 8) {
                            adUser2 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                            adChampName2 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                        }
                        if (k == 9) {
                            supUser2 = matches.get(i).getInfo().getParticipants().get(k).getSummonerName();
                            supChampName2 = matches.get(i).getInfo().getParticipants().get(k).getChampionName();
                        }
                    }
                }

                matchContainer.setChampionName(championName);
                matchContainer.setChampionLevel(championLevel);
                matchContainer.setKills(kills);
                matchContainer.setDeaths(deaths);
                matchContainer.setAssists(assists);
                matchContainer.setKda(kda);

                matchContainer.setItem0(item0);
                matchContainer.setItem1(item1);
                matchContainer.setItem2(item2);
                matchContainer.setItem3(item3);
                matchContainer.setItem4(item4);
                matchContainer.setItem5(item5);
                matchContainer.setItem6(item6);

                matchContainer.setSummoner1Id(mapSummoner1Id);
                matchContainer.setSummoner2Id(mapSummoner2Id);

                matchContainer.setMainPerk(mainPerkURL);
                matchContainer.setSubStyle(subStyleURL);

                matchContainer.setQueueType2(queueType2);

                matchContainer.setTimeAgo(timeAgo);

                matchContainer.setIsWins(isWins);

                matchContainer.setGameMinutes(gameMinutes);
                matchContainer.setGameSeconds(gameSeconds);

                matchContainer.setVisionWard(visionWard);
                matchContainer.setTotalCs(totalCs);
                matchContainer.setCsPerMin(csPerMin);
                matchContainer.setKillsPartRate(killsPartRate);

                matchContainer.setTopUser1(topUser1);
                matchContainer.setTopUser2(topUser2);
                matchContainer.setJungleUser1(jungleUser1);
                matchContainer.setJungleUser2(jungleUser2);
                matchContainer.setMidUser1(midUser1);
                matchContainer.setMidUser2(midUser2);
                matchContainer.setAdUser1(adUser1);
                matchContainer.setAdUser2(adUser2);
                matchContainer.setSupUser1(supUser1);
                matchContainer.setSupUser2(supUser2);

                matchContainer.setTopChampName1(topChampName1);
                matchContainer.setTopChampName2(topChampName2);
                matchContainer.setJungleChampName1(jungleChampName1);
                matchContainer.setJungleChampName2(jungleChampName2);
                matchContainer.setMidChampName1(midChampName1);
                matchContainer.setMidChampName2(midChampName2);
                matchContainer.setAdChampName1(adChampName1);
                matchContainer.setAdChampName2(adChampName2);
                matchContainer.setSupChampName1(supChampName1);
                matchContainer.setSupChampName2(supChampName2);


                matchContainers.add(matchContainer); // MatchContainer 객체를 리스트에 추가
            }
        }catch (RuntimeException e) {

        }


        modelAndView.setViewName("test3"); // 뷰 이름 설정
//        modelAndView.addObject("summonerLevel", summoner.getSummonerLevel());
        modelAndView.addObject("matchContainers", matchContainers);
//        modelAndView.addObject("name", summoner.getName());
//        modelAndView.addObject("profileIcon", profileIcon);


        return modelAndView;
    }
}