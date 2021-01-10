package gg.om.omgg.web;

import gg.om.omgg.api.riot.service.SummonerService;
import gg.om.omgg.dto.SummonerResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RiotApiController {

    private final SummonerService summonerService;

    @GetMapping("/lol/summoners/by-name/{summonerName}")
    public SummonerResponseDTO findByName(@PathVariable("summonerName") String summonerName) {
        return summonerService.findByName(summonerName);
    }
}
