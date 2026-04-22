package org.quizz.politicianparty.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.quizz.politicianparty.model.Politician;
import org.quizz.politicianparty.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quiz")
    public String index(Model model, HttpSession httpSession, HttpServletResponse response) {
        Integer score = (Integer) httpSession.getAttribute("score");
        if (score == null) score = 0;

        List<Long> recentIds = (List<Long>) httpSession.getAttribute("recentIds");
        if (recentIds == null) recentIds = new ArrayList<>();

        //Zufälligen Politiker ziehen aus dem Service
        Politician randomPolitician = quizService.getRandomPolitician(recentIds);

        //recentIds Liste updaten
        recentIds = quizService.recentIdsUpdater(recentIds, randomPolitician);

        List<String> quizParties = quizService.getQuizParties(randomPolitician);

        httpSession.setAttribute("recentIds", recentIds);
        model.addAttribute("politician", randomPolitician);
        model.addAttribute("quizParties", quizParties);
        model.addAttribute("score", score);

        System.out.println("Quizz-Debug: Session-ID: " + httpSession.getId());
        return "quiz";
    }


}
