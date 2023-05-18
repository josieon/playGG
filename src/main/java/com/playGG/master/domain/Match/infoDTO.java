package com.playGG.master.domain.Match;
import java.util.*;

public class infoDTO {
    long gameCreation;
    long gameDuration;
    long gameEndTimestamp;
    public long gameId;
    String gameMode;
    String gameName;
    long gameStartTimestamp;
    String gameType;
    String gameVersion;

    public long getGameEndTimestamp() {
        return gameEndTimestamp;
    }

    int mapId;
    public List<participantDTO> participants;
    String platformId;
    public int queueId;
    public List<teamDTO> teams;
    String tournamentCode;

    public long getGameDuration() {
        return gameDuration;
    }

    public String getDuration() {
        return (gameDuration/60 == 0 ? "" : gameDuration/60+"분 ") + gameDuration%60 + "초";
    }

    public Long getEndTimestamp() {
        //        Date date = new Date();
        //        date.setTime(gameEndTimestamp);
        //        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(date);
        return gameEndTimestamp;
    }

    public int getQueueId() {
        return queueId;
    }

    public List<participantDTO> getParticipants() {
        return participants;
    }

    public String getParticipantsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < 10; i++) {
            sb.append(participants.get(i).getParticipant());
            if(i < 9)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public String getTeams() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(teams.get(0).getTeam() + ",");
        sb.append(teams.get(1).getTeam() + "]");
        return sb.toString();
    }

    public infoDTO() {
    }

    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public void setGameEndTimestamp(long gameEndTimestamp) {
        this.gameEndTimestamp = gameEndTimestamp;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameStartTimestamp(long gameStartTimestamp) {
        this.gameStartTimestamp = gameStartTimestamp;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public void setParticipants(List<participantDTO> participants) {
        this.participants = participants;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public void setTeams(List<teamDTO> teams) {
        this.teams = teams;
    }

    public void setTournamentCode(String tournamentCode) {
        this.tournamentCode = tournamentCode;
    }
}