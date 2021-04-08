package gg.om.omgg.domain.summoner;

import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;

import java.util.List;

public interface SummonerCustomRepository {
    List<SummonerIntegrationInformationResponseDTO> findSummonerIntegrationInformationByName(String summonerName);
}
