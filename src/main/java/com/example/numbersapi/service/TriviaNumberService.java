package com.example.numbersapi.service;

import com.example.numbersapi.entity.TriviaNumber;
import com.example.numbersapi.repository.TriviaNumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TriviaNumberService {

    @Autowired
    private TriviaNumberRepository triviaNumberRepository;

    public TriviaNumber save(TriviaNumber triviaNumber) {
        TriviaNumber triviaNumberSave = triviaNumberRepository.save(triviaNumber);
        log.info("TriviaNumber {} successfully saved!", triviaNumberSave.getTriviaNumber());
        return triviaNumberRepository.save(triviaNumber);
    }

    public List<TriviaNumber> findAll() {
        List<TriviaNumber> triviaNumberList = triviaNumberRepository.findAll();
        log.info("All {} triviaNumbers found!", triviaNumberList.size());
        return triviaNumberList;
    }

    public Optional<TriviaNumber> findByTriviaNumber(long triviaNumber) {
        Optional<TriviaNumber> triviaNumberFound = triviaNumberRepository.findByTriviaNumber(triviaNumber);
        log.info("TriviaNumber successfully found by triviaNumber {}!", triviaNumber);
        return triviaNumberFound;
    }

    @Transactional
    public void deleteByTriviaNumber(long triviaNumber) {
        triviaNumberRepository.deleteByTriviaNumber(triviaNumber);
        log.info("TriviaNumber successfully deleted by triviaNumber {}!", triviaNumber);
    }

    @Transactional
    public void deleteById(long id) {
        triviaNumberRepository.deleteById(id);
        log.info("TriviaNumber successfully deleted by id {}!", id);
    }
}