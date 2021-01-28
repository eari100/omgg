package gg.om.omgg.domain.summoner;

import gg.om.omgg.web.dto.SummonerResponseDTO;

import java.util.List;

public interface SummonerCustomRepository {
    List<SummonerResponseDTO> findSummonerIntegrationInformationByName(String summonerName);
}
