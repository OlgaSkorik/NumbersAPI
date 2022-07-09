package com.example.numbersapi.service;

import com.example.numbersapi.entity.MathNumber;
import com.example.numbersapi.repository.MathNumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MathNumberService {

    @Autowired
    private MathNumberRepository mathNumberRepository;

    public MathNumber save(MathNumber mathNumber) {
        MathNumber mathNumberSave = mathNumberRepository.save(mathNumber);
        log.info("MathNumber {} successfully saved!", mathNumber.getMathNumber());
        return mathNumberRepository.save(mathNumber);
    }

    public List<MathNumber> findAll() {
        List<MathNumber> mathNumberList = mathNumberRepository.findAll();
        log.info("All {} mathNumbers found!", mathNumberList.size());
        return mathNumberList;
    }

    public Optional<MathNumber> findByMathNumber(long mathNumber) {
        Optional<MathNumber> mathNumberFound = mathNumberRepository.findByMathNumber(mathNumber);
        log.info("MathNumber successfully found by mathNumber {}!", mathNumber);
        return mathNumberFound;
    }

    @Transactional
    public void deleteByMathNumber(long mathNumber) {
        mathNumberRepository.deleteByMathNumber(mathNumber);
        log.info("MathNumber successfully deleted by mathNumber {}!", mathNumber);
    }

    @Transactional
    public void deleteById(long id) {
        mathNumberRepository.deleteById(id);
        log.info("MathNumber successfully deleted by id {}!", id);
    }
}
