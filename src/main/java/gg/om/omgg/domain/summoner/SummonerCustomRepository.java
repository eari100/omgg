package gg.om.omgg.domain.summoner;

import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;

public interface SummonerCustomRepository {
    SummonerIntegrationInformationResponseDTO findSummonerIntegrationInformationByName(String summonerName);
}
