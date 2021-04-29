package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.MatchDTO;
import gg.om.omgg.api.riot.dto.MatchListDTO;
import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.match.MatchRepository;
import gg.om.omgg.domain.participant.ParticipantRepository;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class SummonerService {
    private final SummonerRepository summonerRepository;
    private final SummonerParser summonerParser;
    private final MatchRepository matchRepository;
    private final ParticipantRepository participantRepository;

    @Transactional
    public SummonerIntegrationInformationResponseDTO findByName(String name) {
        SummonerIntegrationInformationResponseDTO result = summonerRepository.findSummonerIntegrationInformationByName(name);
        if(result==null) {
            Optional<SummonerDTO> JSONData = summonerParser.getJSONData(name);
            if(JSONData.isPresent()) {
                summonerRepository.save(JSONData.get().toEntity());
                result = summonerRepository.findSummonerIntegrationInformationByName(name);
            }
        }
        return result;
    }

    @Transactional
    public SummonerIntegrationInformationResponseDTO renewData(String name, String id) {
        Optional<SummonerDTO> summonerDTO = summonerParser.getJSONData(name);
        if(summonerDTO.isPresent()) {
            Summoner summoner = summonerDTO.get().toEntity();

            MatchListParser matchListParser = new MatchListParser();
            Optional<MatchListDTO> matchListDTO = matchListParser.getJSONData(summonerDTO.get().getAccountId(),20);

            if(matchListDTO.isPresent()) {
                HashSet<Long> gameIds = matchListDTO.get().getMatches()
                                        .stream()
                                        .map(match->match.getGameId())
                                        .collect(HashSet::new, HashSet::add, HashSet::addAll);

                for(long gameId:gameIds) {
                    MatchDetailParser matchDetailParser = new MatchDetailParser();
                    Optional<MatchDTO> matchDTO = matchDetailParser.getJSONData(gameId);

                    if(matchDTO.isPresent()) {
                        matchRepository.save(matchDTO.get().matchToEntity());
                        summoner.getMatches().add(matchDTO.get().matchToEntity());

                        participantRepository.saveAll(matchDTO.get().participantToEntity());
                    }
                }
            }
            // save()으로 등록,수정 둘다 가능합니다.
            summonerRepository.save(summoner);
        } else {
            Optional<Summoner> summoner = summonerRepository.findById(id);
            if(summoner.isPresent()) {
                // 라이엇서버에 소환사가 존재하지 않는다면 해당 데이터의 summoner.name을 공백으로 update합니다
                summoner.get().update(summoner.get().getAccountId(), summoner.get().getProfileIconId(), summoner.get().getRevisionDate(),
                        "", summoner.get().getPuuid(), summoner.get().getSummonerLevel());
            }
        }
        return summonerRepository.findSummonerIntegrationInformationByName(name);
    }
}
