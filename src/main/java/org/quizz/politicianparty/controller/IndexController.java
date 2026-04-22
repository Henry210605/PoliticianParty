package org.quizz.politicianparty.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    public IndexController() {
    }

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession, HttpServletResponse response) {
        Integer score = (Integer) httpSession.getAttribute("score");
        if (score == null) score = 0;

        model.addAttribute("score", score);

        return "index";
    }
}
