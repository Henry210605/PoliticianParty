package org.quizz.politicianparty.service;

import org.quizz.politicianparty.model.Politician;
import org.quizz.politicianparty.repository.PoliticianRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PoliticianService {
    private final PoliticianRepository politicianRepository;

    public PoliticianService(PoliticianRepository politicianRepository) {
        this.politicianRepository = politicianRepository;
    }

    public Politician getRandomPolitician(){
        Optional<Politician> randomPolitician = politicianRepository.findRandomPolitician();
        if (randomPolitician.isEmpty()){
            throw new RuntimeException("Kein Politiker vorhanden in der Datenbank!");
        }
        return randomPolitician.get();
    }

}
