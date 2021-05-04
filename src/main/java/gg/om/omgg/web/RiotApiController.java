package gg.om.omgg.web;

import gg.om.omgg.api.riot.service.SummonerService;
import gg.om.omgg.web.dto.IntegrationInfoResponseDTO;
import gg.om.omgg.web.dto.MatchesListLeadMoreRequestDTO;
import gg.om.omgg.web.dto.RenewRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RiotApiController {

    private final SummonerService summonerService;

    @PostMapping("/api/renew")
    public List<IntegrationInfoResponseDTO> selectDetail(@RequestBody RenewRequestDTO requestDTO) {
        return summonerService.renewData(requestDTO.getName());
    }

    @PostMapping("/api/matchesLeadMore")
    public List<IntegrationInfoResponseDTO> selectMatchesLeadMore(@RequestBody MatchesListLeadMoreRequestDTO requestDTO) {
        return summonerService.matchesListLeadMore(requestDTO.getName(), requestDTO.getAccountId(), requestDTO.getEndIndex());
    }
}
