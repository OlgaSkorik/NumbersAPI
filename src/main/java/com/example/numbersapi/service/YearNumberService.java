package com.example.numbersapi.service;

import com.example.numbersapi.entity.MathNumber;
import com.example.numbersapi.entity.YearNumber;
import com.example.numbersapi.repository.MathNumberRepository;
import com.example.numbersapi.repository.YearNumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class YearNumberService {

    @Autowired
    private YearNumberRepository yearNumberRepository;

    public YearNumber save(YearNumber yearNumber) {
        YearNumber yearNumberSave = yearNumberRepository.save(yearNumber);
        log.info("YearNumber {} successfully saved!", yearNumber.getYearNumber());
        return yearNumberRepository.save(yearNumber);
    }

    public List<YearNumber> findAll() {
        List<YearNumber> yearNumberList = yearNumberRepository.findAll();
        log.info("All {} yearNumbers found!", yearNumberList.size());
        return yearNumberList;
    }

    public Optional<YearNumber> findByYearNumber(long yearNumber) {
        Optional<YearNumber> yearNumberFound = yearNumberRepository.findByYearNumber(yearNumber);
        log.info("YearNumber successfully found by yearNumber {}!", yearNumber);
        return yearNumberFound;
    }

    @Transactional
    public void deleteByYearNumber(long yearNumber) {
        yearNumberRepository.deleteByYearNumber(yearNumber);
        log.info("YearNumber successfully deleted by yearNumber {}!", yearNumber);
    }

    @Transactional
    public void deleteById(long id) {
        yearNumberRepository.deleteById(id);
        log.info("YearNumber successfully deleted by id {}!", id);
    }
}
