package com.example.numbersapi.repository;

import com.example.numbersapi.entity.MathNumber;
import com.example.numbersapi.entity.YearNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface YearNumberRepository extends JpaRepository<YearNumber, Long> {
    Optional<YearNumber> findByYearNumber(long yearNumber);
    void deleteByYearNumber(long yearNumber);
}
