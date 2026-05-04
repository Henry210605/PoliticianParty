package org.quizz.politicianparty.service;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    public int getHighscore(Cookie[] cookies){
        int highscore = 0;
        for (Cookie cookie : cookies) {
            if ("highscore".equals(cookie.getName())) {
                highscore = Integer.parseInt(cookie.getValue());
            }
        }
        return highscore;
    }

}
