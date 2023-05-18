package com.playGG.master.web;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class indexController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
