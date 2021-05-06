package gg.om.omgg.domain.summoner;

import gg.om.omgg.web.dto.MatchesListLeadMoreResponseDTO;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;

public interface SummonerCustomRepository {
    SummonerIntegrationInformationResponseDTO findSummonerIntegrationInformationByName(String summonerName);
    MatchesListLeadMoreResponseDTO findMatchesListLeadMoreById(String id, int endIndex);
}
