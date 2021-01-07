package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.summoner.SummonerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class SummonerService {
    private final SummonerRepository summonerRepository;

    @Transactional
    public void save(SummonerDTO summonerDTO) {
        summonerRepository.save(summonerDTO.toEntity());
    }
}
