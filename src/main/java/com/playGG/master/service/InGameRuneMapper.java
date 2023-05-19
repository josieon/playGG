package com.playGG.master.service;


import java.util.*;

public class InGameRuneMapper {

    Map<Long, String> runeMap = new HashMap<Long, String>();

    public InGameRuneMapper() {
        // Precision
        runeMap.put(8005L, "Precision/PressTheAttack/PressTheAttack");
        runeMap.put(8008L, "Precision/LethalTempo/LethalTempoTemp");
        runeMap.put(8021L, "Precision/FleetFootwork/FleetFootwork");
        runeMap.put(8010L, "Precision/Conqueror/Conqueror");

        // Domination
        runeMap.put(8112L, "Domination/Electrocute/Electrocute");
        runeMap.put(8124L, "Domination/Predator/Predator");
        runeMap.put(8128L, "Domination/DarkHarvest/DarkHarvest");
        runeMap.put(9923L, "Domination/HailOfBlades/HailOfBlades");

        // Sorcery
        runeMap.put(8214L, "Sorcery/SummonAery/SummonAery");
        runeMap.put(8229L, "Sorcery/ArcaneComet/ArcaneComet");
        runeMap.put(8230L, "Sorcery/PhaseRush/PhaseRush");

        // Resolve
        runeMap.put(8437L, "Resolve/GraspOfTheUndying/GraspOfTheUndying");
        runeMap.put(8439L, "Resolve/VeteranAftershock/VeteranAftershock");
        runeMap.put(8465L, "Resolve/Guardian/Guardian");

        // Inspiration
        runeMap.put(8351L, "Inspiration/GlacialAugment/GlacialAugment");
        runeMap.put(8360L, "Inspiration/UnsealedSpellbook/UnsealedSpellbook");
        runeMap.put(8369L, "Inspiration/FirstStrike/FirstStrike");

        // Primary Runes
        runeMap.put(8000L, "7201_Precision");
        runeMap.put(8100L, "7200_Domination");
        runeMap.put(8200L, "7202_Sorcery");
        runeMap.put(8400L, "7204_Resolve");
        runeMap.put(8300L, "7203_Whimsy");

        runeMap.put(1L, "SummonerBoost");
        runeMap.put(3L, "SummonerExhaust");
        runeMap.put(4L, "SummonerFlash");
        runeMap.put(6L, "SummonerHaste");
        runeMap.put(7L, "SummonerHeal");
        runeMap.put(11L, "SummonerSmite");
        runeMap.put(12L, "SummonerTeleport");
        runeMap.put(13L, "SummonerDot");
        runeMap.put(14L, "SummonerBarrier");
        runeMap.put(21L, "SummonerSnowball");
        runeMap.put(32L, "SummonerMana");

    }

    public String getInGameRuneCode(long runeId) {
        return runeMap.get(runeId);
    }


    public String getInGameSpellCode(long summonerId) {
        return runeMap.get(summonerId);
    }




}