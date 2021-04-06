package gg.om.omgg.web;

import gg.om.omgg.api.riot.service.SummonerService;
import gg.om.omgg.web.dto.RenewRequestDTO;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class RiotApiController {

    private final SummonerService summonerService;

    @PostMapping("/api/renew")
    public Optional<SummonerIntegrationInformationResponseDTO> selectDetail(@RequestBody RenewRequestDTO requestDTO) {
        return summonerService.renewData(requestDTO.getName(), requestDTO.getId());
    }
}
