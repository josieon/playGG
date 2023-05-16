package com.playgg.champion.web;

import com.playgg.champion.service.*;
import jakarta.servlet.http.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class indexController {
    private final ChampionsService championsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("champions", championsService.findAll());

        return "index";
    }

    @GetMapping("champion/{championId}")
    public String championBuild(Model model, @PathVariable Integer championId) {
        model.addAttribute("champion", championsService.findById(championId));
        return "champion-build";
    }
}
