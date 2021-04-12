package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SummonerService {
    private final SummonerRepository summonerRepository;
    private final SummonerParser summonerParser;

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
        Optional<SummonerDTO> JSONData = summonerParser.getJSONData(name);
        if(JSONData.isPresent()) {
            // 등록,수정 둘다 가능합니다.
            summonerRepository.save(JSONData.get().toEntity());
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
