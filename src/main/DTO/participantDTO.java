package DTO;

public class participantDTO {


    int assists;
    int baronKills;
    int bountyLevel;
    int champExperience;
    int champLevel;
    public int championId;
    String championName;
    int championTransform;
    int consumablesPurchased;
    int damageDealtToBuildings;
    int damageDealtToObjectives;
    int damageDealtToTurrets;
    int damageSelfMitigated;
    int deaths;
    int detectorWardsPlaced;
    int doubleKills;
    int dragonKills;
    boolean firstBloodAssist;
    boolean firstBloodKill;
    boolean firstTowerAssist;
    boolean firstTowerKill;
    boolean gameEndedInEarlySurrender;
    boolean gameEndedInSurrender;
    int goldEarned;
    int goldSpent;
    public String individualPosition;
    int inhibitorKills;
    int inhibitorTakedowns;
    int inhibitorLost;
    public int item0;
    public int item1;
    public int item2;
    public int item3;
    public int item4;
    public int item5;
    public int item6;
    int itemsPurchased;
    int killingSprees;
    int kills;
    String lane;
    int largestCriticalStrike;
    int largestKillingSpree;
    int largestMultiKill;
    int longestTimeSpentLiving;
    int magicDamageDealt;
    int magicDamageDealtToChampions;
    int magicDamageTaken;
    int neutralMinionKilled;
    int nexusKills;
    int nexusTakedowns;
    int nexusLost;
    int objectivesStolen;
    int objectivesStolenAssists;
    int participantId;
    int pentaKills;
    public PerksDTO perks;
    int physicalDamageDealt;
    int physicalDamageDealtToChampions;
    int physicalDamageTaken;
    int profileIcon;
    String puuid;
    int quadraKills;
    String riotIdName;
    String riotIdTagline;
    String role;
    int sightWardsBoughtInGame;
    int spell1Casts;
    int spell2Casts;
    int spell3Casts;
    int spell4Casts;
    int summoner1Casts;
    public int summoner1Id;
    int summoner2Casts;
    public int summoner2Id;
    String summonerId;
    int summonerLevel;
    String summonerName;
    boolean teamEarlySurrendered;
    public int teamId;
    String teamPosition;
    int timeCCingOthers;
    int timePlayed;
    int totalDamageDealt;
    int totalDamageDealtToChampions;
    int totalDamageShieldedOnTeammates;
    int totalDamageTaken;
    int totalHeal;
    int totalHealsOnTeammates;
    int totalMinionsKilled;
    int totalTimeCCDealt;
    int totalTimeSpentDead;
    int totalUnitsHealed;
    int tripleKills;
    int trueDamageDealt;
    int trueDamageDealtToChampions;
    int trueDamageTaken;
    int turretKills;
    int turretTakedowns;
    int turretLost;
    int unrealKills;
    int visionScore;
    int visionWardsBoughtInGame;
    int wardsKilled;
    int wardsPlaced;
    public boolean win;

    public String getParticipant() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"puuid\":");
        sb.append("\"" + puuid +"\",");
        sb.append("\"summoner_id\":");
        sb.append("\"" + summonerId +"\",");
        sb.append("\"summoner_name\":");
        sb.append("\"" + summonerName +"\",");
        sb.append("\"kills\":");
        sb.append("\"" + kills +"\",");
        sb.append("\"deaths\":");
        sb.append("\"" + deaths +"\",");
        sb.append("\"assists\":");
        sb.append("\"" + assists +"\",");
        sb.append("\"champ_level\":");
        sb.append("\"" + champLevel +"\",");
        sb.append("\"champion_id\":");
        sb.append("\"" + championId +"\",");
        sb.append("\"champ_name\":");
        sb.append("\"" + championName +"\",");
        sb.append("\"gold\":");
        sb.append("\"" + goldEarned +"\",");
        sb.append("\"item0\":");
        sb.append("\"" + item0 +"\",");
        sb.append("\"item1\":");
        sb.append("\"" + item1 +"\",");
        sb.append("\"item2\":");
        sb.append("\"" + item2 +"\",");
        sb.append("\"item3\":");
        sb.append("\"" + item3 +"\",");
        sb.append("\"item4\":");
        sb.append("\"" + item4 +"\",");
        sb.append("\"item5\":");
        sb.append("\"" + item5 +"\",");
        sb.append("\"item6\":");
        sb.append("\"" + item6 +"\",");
        sb.append("\"position\":");
        sb.append("\"" + individualPosition +"\",");
        sb.append("\"participant_id\":");
        sb.append("\"" + participantId +"\",");
        sb.append("\"perks\":");
        sb.append("\"" + perks.getPerks() +"\",");
        sb.append("\"sub\":");
        sb.append("\"" + perks.getSub() +"\",");
        sb.append("\"spell1\":");
        sb.append("\"" + summoner1Id +"\",");
        sb.append("\"spell2\":");
        sb.append("\"" + summoner2Id +"\",");
        sb.append("\"total_damage_taken\":");
        sb.append("\"" + totalDamageTaken +"\",");
        sb.append("\"total_damage_dealt\":");
        sb.append("\"" + totalDamageDealtToChampions +"\",");
        sb.append("\"shield_and_heal_team\":");
        sb.append("\"" + (totalDamageShieldedOnTeammates + totalHealsOnTeammates) +"\",");
        sb.append("\"CS\":");
        sb.append("\"" + (totalMinionsKilled + neutralMinionKilled) +"\",");
        sb.append("\"vision_ward_bought\":");
        sb.append("\"" + visionWardsBoughtInGame +"\",");
        sb.append("\"vision_score\":");
        sb.append("\"" + visionScore +"\",");
        sb.append("\"ward_placed\":");
        sb.append("\"" + wardsPlaced +"\",");
        sb.append("\"ward_killed\":");
        sb.append("\"" + wardsKilled + "\"}");
        return sb.toString();
    }

    public participantDTO() {
    }

    public int getAssists() {
        return assists;
    }

    public int getBaronKills() {
        return baronKills;
    }

    public int getBountyLevel() {
        return bountyLevel;
    }

    public int getChampExperience() {
        return champExperience;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public int getChampionId() {
        return championId;
    }

    public String getChampionName() {
        return championName;
    }

    public int getChampionTransform() {
        return championTransform;
    }

    public int getConsumablesPurchased() {
        return consumablesPurchased;
    }

    public int getDamageDealtToBuildings() {
        return damageDealtToBuildings;
    }

    public int getDamageDealtToObjectives() {
        return damageDealtToObjectives;
    }

    public int getDamageDealtToTurrets() {
        return damageDealtToTurrets;
    }

    public int getDamageSelfMitigated() {
        return damageSelfMitigated;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getDetectorWardsPlaced() {
        return detectorWardsPlaced;
    }

    public int getDoubleKills() {
        return doubleKills;
    }

    public int getDragonKills() {
        return dragonKills;
    }

    public boolean isFirstBloodAssist() {
        return firstBloodAssist;
    }

    public boolean isFirstBloodKill() {
        return firstBloodKill;
    }

    public boolean isFirstTowerAssist() {
        return firstTowerAssist;
    }

    public boolean isFirstTowerKill() {
        return firstTowerKill;
    }

    public boolean isGameEndedInEarlySurrender() {
        return gameEndedInEarlySurrender;
    }

    public boolean isGameEndedInSurrender() {
        return gameEndedInSurrender;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

    public String getIndividualPosition() {
        return individualPosition;
    }

    public int getInhibitorKills() {
        return inhibitorKills;
    }

    public int getInhibitorTakedowns() {
        return inhibitorTakedowns;
    }

    public int getInhibitorLost() {
        return inhibitorLost;
    }

    public int getItem0() {
        return item0;
    }

    public int getItem1() {
        return item1;
    }

    public int getItem2() {
        return item2;
    }

    public int getItem3() {
        return item3;
    }

    public int getItem4() {
        return item4;
    }

    public int getItem5() {
        return item5;
    }

    public int getItem6() {
        return item6;
    }

    public int getItemsPurchased() {
        return itemsPurchased;
    }

    public int getKillingSprees() {
        return killingSprees;
    }

    public int getKills() {
        return kills;
    }

    public String getLane() {
        return lane;
    }

    public int getLargestCriticalStrike() {
        return largestCriticalStrike;
    }

    public int getLargestKillingSpree() {
        return largestKillingSpree;
    }

    public int getLargestMultiKill() {
        return largestMultiKill;
    }

    public int getLongestTimeSpentLiving() {
        return longestTimeSpentLiving;
    }

    public int getMagicDamageDealt() {
        return magicDamageDealt;
    }

    public int getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    public int getMagicDamageTaken() {
        return magicDamageTaken;
    }

    public int getNeutralMinionKilled() {
        return neutralMinionKilled;
    }

    public int getNexusKills() {
        return nexusKills;
    }

    public int getNexusTakedowns() {
        return nexusTakedowns;
    }

    public int getNexusLost() {
        return nexusLost;
    }

    public int getObjectivesStolen() {
        return objectivesStolen;
    }

    public int getObjectivesStolenAssists() {
        return objectivesStolenAssists;
    }

    public int getParticipantId() {
        return participantId;
    }

    public int getPentaKills() {
        return pentaKills;
    }

    public PerksDTO getPerks() {
        return perks;
    }

    public int getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }

    public int getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }

    public int getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    public int getProfileIcon() {
        return profileIcon;
    }

    public String getPuuid() {
        return puuid;
    }

    public int getQuadraKills() {
        return quadraKills;
    }

    public String getRiotIdName() {
        return riotIdName;
    }

    public String getRiotIdTagline() {
        return riotIdTagline;
    }

    public String getRole() {
        return role;
    }

    public int getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }

    public int getSpell1Casts() {
        return spell1Casts;
    }

    public int getSpell2Casts() {
        return spell2Casts;
    }

    public int getSpell3Casts() {
        return spell3Casts;
    }

    public int getSpell4Casts() {
        return spell4Casts;
    }

    public int getSummoner1Casts() {
        return summoner1Casts;
    }

    public int getSummoner1Id() {
        return summoner1Id;
    }

    public int getSummoner2Casts() {
        return summoner2Casts;
    }

    public int getSummoner2Id() {
        return summoner2Id;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public boolean isTeamEarlySurrendered() {
        return teamEarlySurrendered;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getTeamPosition() {
        return teamPosition;
    }

    public int getTimeCCingOthers() {
        return timeCCingOthers;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public int getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public int getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    public int getTotalDamageShieldedOnTeammates() {
        return totalDamageShieldedOnTeammates;
    }

    public int getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public int getTotalHeal() {
        return totalHeal;
    }

    public int getTotalHealsOnTeammates() {
        return totalHealsOnTeammates;
    }

    public int getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public int getTotalTimeCCDealt() {
        return totalTimeCCDealt;
    }

    public int getTotalTimeSpentDead() {
        return totalTimeSpentDead;
    }

    public int getTotalUnitsHealed() {
        return totalUnitsHealed;
    }

    public int getTripleKills() {
        return tripleKills;
    }

    public int getTrueDamageDealt() {
        return trueDamageDealt;
    }

    public int getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    public int getTrueDamageTaken() {
        return trueDamageTaken;
    }

    public int getTurretKills() {
        return turretKills;
    }

    public int getTurretTakedowns() {
        return turretTakedowns;
    }

    public int getTurretLost() {
        return turretLost;
    }

    public int getUnrealKills() {
        return unrealKills;
    }

    public int getVisionScore() {
        return visionScore;
    }

    public int getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    public int getWardsKilled() {
        return wardsKilled;
    }

    public int getWardsPlaced() {
        return wardsPlaced;
    }

    public boolean isWin() {
        return win;
    }

}
