package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.MatchListDTO;
import gg.om.omgg.domain.match.MatchReferenceRepository;
import gg.om.omgg.domain.summoner.Summoner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MatchReferenceService {
    private final MatchReferenceRepository matchReferenceRepository;
    @Transactional
    public void saveAll(MatchListDTO matchListDTO, Summoner summoner) { matchReferenceRepository.saveAll(matchListDTO.toEntity(summoner)); }
}
