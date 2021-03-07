package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.MatchListDTO;
import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SummonerService {
    private final SummonerRepository summonerRepository;
    private final SummonerParser summonerParser;
    private final MatchReferenceService matchReferenceService;
    @Transactional
    public void save(SummonerDTO summonerDTO) { summonerRepository.save(summonerDTO.toEntity()); }

    @Transactional
    public List<SummonerIntegrationInformationResponseDTO> findByName(String name) {
        List<SummonerIntegrationInformationResponseDTO> result = summonerRepository.findSummonerIntegrationInformationByName(name);

        if(result.isEmpty()) {
            Optional<SummonerDTO> summonerToJSON = summonerParser.getJSONData(name);
            if( !summonerToJSON.isEmpty() ) {
                save(summonerToJSON.get());
                return summonerRepository.findSummonerIntegrationInformationByName(name);
            }
        }
            return result;
    }

    @Transactional
    public List<SummonerIntegrationInformationResponseDTO> renewData(String name, String id) {
        Optional<SummonerDTO> summonerToJSON = summonerParser.getJSONData(name);

        if( !summonerToJSON.isEmpty() ) {
            update(id, summonerToJSON.get());
            MatchListParser matchListParser = new MatchListParser();
            Optional<MatchListDTO> matchListToJSON = matchListParser.getJSONData(summonerToJSON.get().getAccountId());

            matchReferenceService.saveAll(matchListToJSON.get(), summonerToJSON.get().toEntity());
        }

        return findByName(name);
    }

    @Transactional
    public String update(String id, SummonerDTO summonerDTO) {
        Summoner summoner = summonerRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 소환사가 존재하지 않습니다. id = "+id));
        summoner.update(
                summonerDTO.getAccountId(), summonerDTO.getProfileIconId(), summonerDTO.getRevisionDate(),
                summonerDTO.getName(), summonerDTO.getPuuid(), summonerDTO.getSummonerLevel()
        );
        return id;
    }
}
