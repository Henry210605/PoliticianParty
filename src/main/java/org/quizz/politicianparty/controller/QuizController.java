package org.quizz.politicianparty.controller;

import org.quizz.politicianparty.model.Politician;
import org.quizz.politicianparty.service.PoliticianService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {
    private final PoliticianService politicianService;

    public QuizController(PoliticianService politicianService) {
        this.politicianService = politicianService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Politician randomPolititian = politicianService.getRandomPolitician();
        model.addAttribute("politician",randomPolititian);
        return "index";
    }
}
