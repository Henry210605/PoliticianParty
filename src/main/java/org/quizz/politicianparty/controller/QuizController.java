package org.quizz.politicianparty.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.quizz.politicianparty.model.Politician;
import org.quizz.politicianparty.service.PoliticianService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizController {
    private final PoliticianService politicianService;

    public QuizController(PoliticianService politicianService) {
        this.politicianService = politicianService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession, HttpServletResponse response) {
        List<Long> recentIds = (List<Long>) httpSession.getAttribute("recentIds");
        if (recentIds == null) recentIds = new ArrayList<>();

        System.out.println("Quizz-Debug: recentIds aus Session: " + recentIds);

        Politician randomPolititian = politicianService.getRandomPolitician();
        //System.out.println("Quizz-Debug: erster Zufalls-Politiker: " + randomPolititian.getId());

        while (recentIds.contains(randomPolititian.getId())) {
            randomPolititian = politicianService.getRandomPolitician();
        //    System.out.println("Quizz-Debug: neu gezogen: " + randomPolititian.getId());
        }

        System.out.println("Quizz-Debug: finaler Politiker: " + randomPolititian.getId());

        //Liste aufräumen wenn zu viele Elemente
        recentIds.add(randomPolititian.getId());
        if (recentIds.size() > (politicianService.getAllPoliticiansSize() - 2)) {
            recentIds.removeFirst();
        }

        List<String> quizParties = politicianService.getQuizParties(randomPolititian);

        httpSession.setAttribute("recentIds", recentIds);
        model.addAttribute("politician",randomPolititian);
        model.addAttribute("quizParties", quizParties);

        System.out.println("Quizz-Debug: Session-ID: " + httpSession.getId());
        return "index";
    }
}
