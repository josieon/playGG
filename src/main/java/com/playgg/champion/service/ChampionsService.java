package com.playgg.champion.service;

import com.playgg.champion.domain.champions.*;
import com.playgg.champion.web.dto.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

@RequiredArgsConstructor
@Service
public class ChampionsService {
    private final ChampionsRepository championsRepository;

    @Transactional(readOnly = true)
    public List<ChampsListResponseDto> findAll() {
        return championsRepository.findAll().stream()
                .map(ChampsListResponseDto::new)
                .sorted(Comparator.comparing(ChampsListResponseDto::getWinRate))
                .collect(Collectors.toList());
    }

    public ChampionResponseDto findById(Integer championId) {
        return new ChampionResponseDto((Champion) championsRepository.findById(championId).get());
    }
}
