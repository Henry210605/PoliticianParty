package org.quizz.politicianparty.controller;

import jakarta.servlet.http.HttpSession;
import org.quizz.politicianparty.model.Politician;
import org.quizz.politicianparty.service.PoliticianService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QuizController {
    private final PoliticianService politicianService;

    public QuizController(PoliticianService politicianService) {
        this.politicianService = politicianService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession) {
        Politician randomPolititian = politicianService.getRandomPolitician();
        List<String> quizParties = politicianService.getQuizParties(randomPolititian);

        //letzte PolitikerID der Session
        Long lastId = (Long) httpSession.getAttribute("lastId");

        //Solange neu Suchen bis es nicht der letzte Politiker ist
        while (lastId != null && randomPolititian.getId().equals(lastId)) {
            randomPolititian = politicianService.getRandomPolitician();
        }

        httpSession.setAttribute("lastId", randomPolititian.getId());
        model.addAttribute("politician",randomPolititian);
        model.addAttribute("quizParties", quizParties);
        return "index";
    }
}
