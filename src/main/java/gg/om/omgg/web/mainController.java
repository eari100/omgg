package gg.om.omgg.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class mainController {
    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/userName={name}")
    public String selectDetail(@PathVariable("name") String name) {
        return "summonerDetail";
    }
}
