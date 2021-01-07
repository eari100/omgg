package gg.om.omgg.web;

import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.api.riot.service.SummonerParser;
import gg.om.omgg.api.riot.service.SummonerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
