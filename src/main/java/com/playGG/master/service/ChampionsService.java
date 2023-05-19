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
    private final SpellsRepository spellsRepository;
    private final CounterRepository counterRepository;
    private final ItemRepository itemRepository;
    private final ChampionIdMapper championIdMapper = new ChampionIdMapper();

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
        List<counter_statistic> counters = counterRepository.findAllById(championId);
        ChampionResponseDto res = ChampionResponseDto.builder()
                .statistic(c)
                .winRate((float)Math.round(10000 * c.getWins() / c.getChoices()) / 100)
                .banRate((float)Math.round(10000 * c.getBans() / gameCount) / 100)
                .pickRate((float)Math.round(10000 * c.getChoices() / gameCount) / 100)
                .perks(perksRepository.findAllById(championId).stream()
                        .sorted(Comparator.comparing(perk_statistic::getChoices).reversed())
                        .map(e -> Perks.builder()
                                        .pickRate((float)Math.round(10000 * e.getChoices() / gameCount) / 100)
                                        .winRate((float)Math.round(10000 * e.getWins() / e.getChoices()) / 100)
                                        .perk(Arrays.stream(e.getPerkPK().getPerks().split(","))
                                                .map(p -> new Perk(Integer.parseInt(p), Integer.parseInt(p) > 7000 ?
                                                        "https://opgg-static.akamaized.net/meta/images/lol/perk/" + p + ".png"
                                                        : "https://opgg-static.akamaized.net/meta/images/lol/perkShard/" + p +".png"))
                                                .collect(Collectors.toList()))
                                        .build()
//                            Perks.builder()
//                                    .pickRate()
//                                    .winRate()
//                                    .perks(e.getPerks())
//                                    .build();
                        )
                        .collect(Collectors.toList()))
                .spells(spellsRepository.findAllBy(championId).stream()
                        .map(e -> Spells.builder()
                                .picCount(e.getChoices())
                                .winRate((float) Math.round(10000 * e.getWins() / e.getChoices()) / 100)
                                .pickRate((float) Math.round(10000 * e.getChoices() / c.getChoices()) / 100)
                                .build())
                        .collect(Collectors.toList()))
                .counters_easy(counters.stream()
                        .sorted(new Comparator<counter_statistic>() {
                            @Override
                            public int compare(counter_statistic o1, counter_statistic o2) {
                                return (float)o1.getWins()/o1.getChoices() > (float)o2.getWins()/o2.getChoices() ? -1 : 1;
                            }
                        })
                        .limit(5)
                        .map(e -> CounterDto.builder()
                                .championId(e.getCounterPK().getCounterId())
                                .winRate((float)Math.round(10000*e.getWins()/e.getChoices())/100)
                                .build())
                        .collect(Collectors.toList()))
                .counters_hard(counters.stream()
                        .sorted(new Comparator<counter_statistic>() {
                            @Override
                            public int compare(counter_statistic o1, counter_statistic o2) {
                                return (float)o1.getWins()/o1.getChoices() < (float)o2.getWins()/o2.getChoices() ? -1 : 1;
                            }
                        })
                        .limit(5)
                        .map(e -> CounterDto.builder()
                                .championId(e.getCounterPK().getCounterId())
                                .winRate((float)Math.round(10000*e.getWins()/e.getChoices())/100)
                                .build())
                        .collect(Collectors.toList()))
                .items(itemRepository.findAllById(championId).stream()
                        .sorted(new Comparator<item_statistic>() {
                            @Override
                            public int compare(item_statistic o1, item_statistic o2) {
                                return (float)o1.getWins()/o1.getChoices() > (float)o2.getWins()/o2.getChoices() ? -1 : 1;
                            }
                        })
                        .map(e -> Items.builder()
                                .itemId(e.getItemPK().getItemId())
                                .pickRate((float)Math.round(10000 * e.getChoices() / c.getChoices()) / 100)
                                .winRate((float)Math.round(10000 * e.getWins() / e.getChoices()) / 100)
                                .build())
                        .collect(Collectors.toList()))
                .profileImage(championIdMapper.getChampImgURL(championId))
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
