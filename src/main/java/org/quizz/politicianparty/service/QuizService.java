package org.quizz.politicianparty.service;

import org.quizz.politicianparty.model.Politician;
import org.quizz.politicianparty.repository.PoliticianRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    private final PoliticianRepository politicianRepository;

    public QuizService(PoliticianRepository politicianRepository) {
        this.politicianRepository = politicianRepository;
    }

    public Politician getRandomPolitician(List<Long> recentIds){
        Politician randomPolitician = getRandomPoliticianFromRepo();
        //System.out.println("Quizz-Debug: erster Zufalls-Politiker: " + randomPolititian.getId());

        while (recentIds.contains(randomPolitician.getId())) {
            randomPolitician = getRandomPoliticianFromRepo();
            //System.out.println("Quizz-Debug: neu gezogen: " + randomPolititian.getId());
        }

        System.out.println("Quizz-Debug: finaler Politiker: " + randomPolitician.getId());

        return randomPolitician;
    }

    //Hilfsmethode für getRandomPolitician
    private Politician getRandomPoliticianFromRepo(){
        Optional<Politician> randomPoliticianOptional = politicianRepository.getRandomPolitician();
        if (randomPoliticianOptional.isEmpty()){
            throw new RuntimeException("Kein Politiker vorhanden in der Datenbank!");
        }

        return randomPoliticianOptional.get();
    }

    public List<Long> recentIdsUpdater(List<Long> recentIds, Politician randomPolitician) {
        //System.out.println("Quizz-Debug: recentIds aus Session: " + recentIds);

        //Liste aufräumen wenn zu viele Elemente
        recentIds.add(randomPolitician.getId());
        //kleine Menge an Politikern
        /*
        if (recentIds.size() > (politicianService.getAllPoliticiansSize() - 2)) {
            recentIds.removeFirst();
        }
         */
        //große Menge an Politikern
        if (recentIds.size() > (getAllPoliticiansSize() - 10)) {
            recentIds.removeFirst();
        }

        return recentIds;
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

    public long getAllPoliticiansSize() {
        return politicianRepository.count();
    }

    public boolean isCorrectAnswer(String selectedParty, String correctParty) {
        return selectedParty.equals(correctParty);
    }
}
