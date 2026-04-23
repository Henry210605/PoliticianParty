package org.quizz.politicianparty.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    public IndexController() {
    }

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession, HttpServletResponse response) {
        Integer score = (Integer) httpSession.getAttribute("score");
        if (score == null) score = 0;

        httpSession.setAttribute("score", score);
        model.addAttribute("score", score);

        return "index";
    }

    @PostMapping("/quiz/start")
    public String startGame(HttpSession httpSession){
        httpSession.removeAttribute("score");
        httpSession.setAttribute("gameOver", false);

        return "redirect:/quiz";
    }
}
