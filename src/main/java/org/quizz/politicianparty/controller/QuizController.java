package org.quizz.politicianparty.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.quizz.politicianparty.model.Politician;
import org.quizz.politicianparty.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        //bei illegalem Aufruf der nicht über den "Spiel starten" zurück auf die Startseite gehen
        Boolean gameOver = (Boolean) httpSession.getAttribute("gameOver");
        if (gameOver == null || gameOver) return "redirect:/";

        Integer score = (Integer) httpSession.getAttribute("score");

        if (score == null) score = 0;

        List<Long> recentIds = (List<Long>) httpSession.getAttribute("recentIds");
        if (recentIds == null) recentIds = new ArrayList<>();

        //Zufälligen Politiker ziehen aus dem Service
        Politician randomPolitician = quizService.getRandomPolitician(recentIds);

        //recentIds Liste updaten
        recentIds = quizService.recentIdsUpdater(recentIds, randomPolitician);

        List<String> quizParties = quizService.getQuizParties(randomPolitician);

        model.addAttribute("quizParties", quizParties);
        model.addAttribute("politician", randomPolitician);
        model.addAttribute("score", score);
        httpSession.setAttribute("politician", randomPolitician);
        httpSession.setAttribute("score", score);
        httpSession.setAttribute("recentIds", recentIds);

        System.out.println("Quizz-Debug: Session-ID: " + httpSession.getId());
        return "quiz";
    }

    @PostMapping("/quiz/answer")
    public String checkAnswer(@RequestParam String selectedParty, HttpSession httpSession){
        Integer score = (Integer) httpSession.getAttribute("score");
        Politician currentPolitician = (Politician) httpSession.getAttribute("politician");
        String correctParty = currentPolitician.getParty();

        //richtige Antwort
        if (quizService.isCorrectAnswer(selectedParty, correctParty)){
            score++;
            httpSession.setAttribute("score", score);
            return "redirect:/quiz";
        }

        //game Over
        httpSession.removeAttribute("recentIds");
        httpSession.setAttribute("lastPolitician", currentPolitician);
        httpSession.removeAttribute("politician");
        httpSession.setAttribute("gameOver", true);

        return "redirect:/";
    }

}
