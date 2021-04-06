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
    public void save(SummonerDTO summonerDTO) { summonerRepository.save(summonerDTO.toEntity()); }

    @Transactional
    public Optional<SummonerIntegrationInformationResponseDTO> findByName(String name) {
        Optional<Summoner> result = summonerRepository.findByName(name);
        if(result.isEmpty()) {
            Optional<SummonerDTO> JSONData = summonerParser.getJSONData(name);
            if(JSONData.isEmpty()) {
                return Optional.empty();
            } else {
                save(JSONData.get());
                return Optional.of(new SummonerIntegrationInformationResponseDTO(summonerRepository.findByName(name).get()));
            }
        } else {
            return Optional.of(new SummonerIntegrationInformationResponseDTO(result.get()));
        }
    }

    @Transactional
    public Optional<SummonerIntegrationInformationResponseDTO> renewData(String name, String id) {

        summonerRepository.deleteById(id);

        Optional<SummonerDTO> JSONData = summonerParser.getJSONData(name);
        if( !JSONData.isEmpty() ) {
            summonerRepository.save(JSONData.get().toEntity());
        }

        return findByName(name);
    }
}
