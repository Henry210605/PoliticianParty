package org.quizz.politicianparty.service;

import org.quizz.politicianparty.model.Politician;
import org.quizz.politicianparty.repository.PoliticianRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<String> getQuizParties(Politician currentPolitician){
        List<String> quizPartys = new ArrayList<>();
        List<String> allDistinctParties = politicianRepository.getAllDistinctParties();

        quizPartys.add(currentPolitician.getParty());
        allDistinctParties.remove(currentPolitician.getParty());
        Collections.shuffle(allDistinctParties);
        for (int i =0;i<3;i++){
            quizPartys.add(allDistinctParties.get(i));
        }
        Collections.shuffle(quizPartys);

        return quizPartys;
    }

}
