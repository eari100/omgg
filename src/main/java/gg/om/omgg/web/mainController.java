package gg.om.omgg.web;

import gg.om.omgg.api.riot.service.SummonerService;
import gg.om.omgg.web.dto.IntegrationInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class mainController {

    private final SummonerService summonerService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/userName={name}")
    public String selectDetail(Model model, @PathVariable("name") String summonerName) {

        List<IntegrationInfoResponseDTO> dto = summonerService.findByName(summonerName);
        model.addAttribute("IntegrationInfo", dto);

        return "summonerDetail";
    }
}
