package gg.om.omgg.api.riot.service;

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
    @Transactional
    public void save(SummonerDTO summonerDTO) { summonerRepository.save(summonerDTO.toEntity()); }

    @Transactional
    public List<SummonerIntegrationInformationResponseDTO> findByName(String name) {
        List<SummonerIntegrationInformationResponseDTO> result = summonerRepository.findSummonerIntegrationInformationByName(name);
        if(result.isEmpty()) {
            Optional<SummonerDTO> JSONData = summonerParser.getJSONData(name);
            if (!JSONData.isEmpty()) {
                save(JSONData.get());
                result = summonerRepository.findSummonerIntegrationInformationByName(name);
            }
        }
        return result;
    }

    @Transactional
    public List<SummonerIntegrationInformationResponseDTO> renewData(String name, String id) {

        summonerRepository.deleteById(id);

        Optional<SummonerDTO> JSONData = summonerParser.getJSONData(name);
        if( !JSONData.isEmpty() ) {
            summonerRepository.save(JSONData.get().toEntity());
        }

        return findByName(name);
    }

    @Transactional
    public String update(String id, Summoner requestDto) {
        Summoner summoner = summonerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 소환사가 없습니다. id=" + id));

        summoner.update(requestDto.getAccountId(), requestDto.getProfileIconId(), requestDto.getRevisionDate(),
                requestDto.getName(), requestDto.getPuuid(), requestDto.getSummonerLevel());

        return id;
    }
}
