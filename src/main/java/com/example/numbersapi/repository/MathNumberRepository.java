package com.example.numbersapi.repository;

import com.example.numbersapi.entity.MathNumber;
import com.example.numbersapi.entity.TriviaNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MathNumberRepository extends JpaRepository<MathNumber, Long> {

    Optional<MathNumber> findByMathNumber(long mathNumber);

    void deleteByMathNumber(long mathNumber);
}
