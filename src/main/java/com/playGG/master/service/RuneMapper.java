package com.playGG.master.service;

import java.util.HashMap;
import java.util.Map;

public class RuneMapper {

    Map<Integer, String> runeMap = new HashMap<Integer, String>();

    public RuneMapper() {
        // Precision
        runeMap.put(8005, "Precision/PressTheAttack/PressTheAttack");
        runeMap.put(8008, "Precision/LethalTempo/LethalTempoTemp");
        runeMap.put(8021, "Precision/FleetFootwork/FleetFootwork");
        runeMap.put(8010, "Precision/Conqueror/Conqueror");

        // Domination
        runeMap.put(8112, "Domination/Electrocute/Electrocute");
        runeMap.put(8124, "Domination/Predator/Predator");
        runeMap.put(8128, "Domination/DarkHarvest/DarkHarvest");
        runeMap.put(9923, "Domination/HailOfBlades/HailOfBlades");

        // Sorcery
        runeMap.put(8214, "Sorcery/SummonAery/SummonAery");
        runeMap.put(8229, "Sorcery/ArcaneComet/ArcaneComet");
        runeMap.put(8230, "Sorcery/PhaseRush/PhaseRush");

        // Resolve
        runeMap.put(8437, "Resolve/GraspOfTheUndying/GraspOfTheUndying");
        runeMap.put(8439, "Resolve/VeteranAftershock/VeteranAftershock");
        runeMap.put(8465, "Resolve/Guardian/Guardian");

        // Inspiration
        runeMap.put(8351, "Inspiration/GlacialAugment/GlacialAugment");
        runeMap.put(8360, "Inspiration/UnsealedSpellbook/UnsealedSpellbook");
        runeMap.put(8369, "Inspiration/FirstStrike/FirstStrike");

        // Primary Runes
        runeMap.put(8000, "7201_Precision");
        runeMap.put(8100, "7200_Domination");
        runeMap.put(8200, "7202_Sorcery");
        runeMap.put(8400, "7204_Resolve");
        runeMap.put(8300, "7203_Whimsy");

        runeMap.put(1, "SummonerBoost");
        runeMap.put(3, "SummonerExhaust");
        runeMap.put(4, "SummonerFlash");
        runeMap.put(6, "SummonerHaste");
        runeMap.put(7, "SummonerHeal");
        runeMap.put(11, "SummonerSmite");
        runeMap.put(12, "SummonerTeleport");
        runeMap.put(13, "SummonerDot");
        runeMap.put(14, "SummonerBarrier");
        runeMap.put(21, "SummonerSnowball");
        runeMap.put(32, "SummonerMana");

    }

    public String getRuneCode(int runeId) {
        return runeMap.get(runeId);
    }
    public String getInGameRuneCode(long runeId) {
        return runeMap.get(runeId);
    }

    public String getSpellCode(int summonerId) {
        return runeMap.get(summonerId);
    }

    public String getInGameSpellCode(long summonerId) {
        return runeMap.get(summonerId);
    }




}

