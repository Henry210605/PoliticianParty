package org.quizz.politicianparty.controller;

import jakarta.servlet.http.*;
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
    public String index(Model model, HttpSession httpSession, HttpServletRequest request) {
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

        //Highscore aus Cookie lesen
        int highscore = 0;
        if (request.getCookies() != null) {
            highscore = quizService.getHighscore(request.getCookies());    
        }

        model.addAttribute("quizParties", quizParties);
        model.addAttribute("politician", randomPolitician);
        model.addAttribute("score", score);
        model.addAttribute("highscore", highscore);
        httpSession.setAttribute("politician", randomPolitician);
        httpSession.setAttribute("score", score);
        httpSession.setAttribute("recentIds", recentIds);

        System.out.println("Quizz-Debug: Session-ID: " + httpSession.getId());
        return "quiz";
    }

    @PostMapping("/quiz/answer")
    public String checkAnswer(@RequestParam String selectedParty, HttpSession httpSession, HttpServletResponse response, HttpServletRequest request){
        Integer score = (Integer) httpSession.getAttribute("score");
        Politician currentPolitician = (Politician) httpSession.getAttribute("politician");
        String correctParty = currentPolitician.getParty();

        //richtige Antwort
        if (quizService.isCorrectAnswer(selectedParty, correctParty)){
            score++;
            httpSession.setAttribute("score", score);

            //Highscore aus Cookies laden und prüfen ob Highscore gestiegen ist
            int highscore = 0;
            if (request.getCookies() != null) {
                highscore = quizService.getHighscore(request.getCookies());
            }
            highscore = quizService.compareHighscoreWithScore(score, highscore);

            //Cookie für Highscore setzen
            Cookie highscoreCookie = new Cookie("highscore", String.valueOf(highscore));
            highscoreCookie.setPath("/");
            highscoreCookie.setMaxAge(60 * 60 * 24 * 365); // 1 Jahr gültig
            response.addCookie(highscoreCookie);

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
