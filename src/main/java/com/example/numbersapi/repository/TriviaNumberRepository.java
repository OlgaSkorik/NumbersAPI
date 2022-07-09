package com.example.numbersapi.repository;

import com.example.numbersapi.entity.TriviaNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TriviaNumberRepository extends JpaRepository<TriviaNumber, Long> {

    Optional<TriviaNumber> findByTriviaNumber(long triviaNumber);

    void deleteByTriviaNumber(long triviaNumber);
}
