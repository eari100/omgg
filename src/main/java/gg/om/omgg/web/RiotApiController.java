package gg.om.omgg.web;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.api.riot.service.SummonerParser;
import gg.om.omgg.api.riot.service.SummonerService;
import gg.om.omgg.dto.SummonerResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RiotApiController {

    private final SummonerService summonerService;
    private final SummonerParser summonerParser;

    @PostMapping("/lol/summoners/by-name/{summonerName}")
    public void saveSummoner(@PathVariable("summonerName") String summonerName, @RequestBody SummonerDTO summonerDTO) {

        summonerDTO = summonerParser.getJSONData(summonerName);
        summonerService.save(summonerDTO);
    }

    @GetMapping("/lol/summoners/by-name/{summonerName}")
    public SummonerResponseDTO findByName(@PathVariable("summonerName") String summonerName) {
        return summonerService.findByName(summonerName);
    }
}
