package org.quizz.politicianparty.repository;

import org.quizz.politicianparty.model.Politician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoliticianRepository extends JpaRepository<Politician, Long> {

    @Query(value = "SELECT * FROM politicians ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Politician> getRandomPolitician();

    @Query(value = "SELECT DISTINCT party from politicians", nativeQuery = true)
    List<String> getAllDistinctParties();

}
