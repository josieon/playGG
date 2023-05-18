package com.playGG.master.service;

import com.playGG.master.domain.Statistic.*;
import com.playGG.master.web.DTO.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

@RequiredArgsConstructor
@Service
public class ChampionsService {
    private final ChampionsRepository championsRepository;
    private final PerksRepository perksRepository;

    @Transactional(readOnly = true)
    public List<ChampsListResponseDto> findAll() {
        List<champion_statistic> list = championsRepository.findAll();
        int gameCount = list.stream().map(e -> e.getChoices()).reduce(0, (a, b) -> a+b) / 10;
        return list.stream()
                .map(e -> new ChampsListResponseDto(e, (float)e.getChoices() / gameCount, (float)e.getBans() / gameCount))
//                .forEach(e -> e.setBanRate((float)e.getBans() / gameCount))
                .sorted(Comparator.comparing(ChampsListResponseDto::getWinRate).reversed())
                .collect(Collectors.toList());
    }

    public ChampionResponseDto findById(Integer championId) {
        champion_statistic c = championsRepository.findById(championId).get();
        int gameCount = championsRepository.findAll().stream().map(e -> e.getChoices()).reduce(0, (a, b) -> a + b) / 10;
        ChampionResponseDto res = ChampionResponseDto.builder()
                .statistic(c)
                .winRate((float)Math.round(10000 * c.getWins() / c.getChoices()) / 100)
                .banRate((float)Math.round(10000 * c.getBans() / gameCount) / 100)
                .pickRate((float)Math.round(10000 * c.getChoices() / gameCount) / 100)
                .perks(perksRepository.findAllById(championId).stream()
                        .sorted(Comparator.comparing(perk_statistic::getChoices).reversed())
                        .map(e -> new Perks(e, c.getChoices()))
//                            Perks.builder()
//                                    .pickRate()
//                                    .winRate()
//                                    .perks(e.getPerks())
//                                    .build();
//                        )
                        .collect(Collectors.toList()))
                .build();
//        List<perk_statistic> p = perksRepository.findAllById(championId);
//        for(perk_statistic a : p) {
//            System.out.println(a.getChoices());
//        }
//        res.getPerks().stream().map(e -> e.getWinRate()).forEach(System.out::println);
        return res;
//        return new ChampionResponseDto((champion_statistic) championsRepository.findById(championId).get());
    }
}
