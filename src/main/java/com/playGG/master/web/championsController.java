package com.playGG.master.web;

import com.playGG.master.service.*;
import jakarta.servlet.http.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class championsController {
    private final ChampionsService championsService;
    private final HttpSession httpSession;

    @GetMapping("/champions")
    public String index(Model model) {
        model.addAttribute("champions", championsService.findAll());

        return "champions";
    }

    @GetMapping("champions/{championId}")
    public String championBuild(Model model, @PathVariable Integer championId) {
        model.addAttribute("champion", championsService.findById(championId));
        return "champion-build";
    }
}
