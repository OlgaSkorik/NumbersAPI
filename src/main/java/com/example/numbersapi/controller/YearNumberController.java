package com.example.numbersapi.controller;

import com.example.numbersapi.entity.MathNumber;
import com.example.numbersapi.entity.YearNumber;
import com.example.numbersapi.service.MathNumberService;
import com.example.numbersapi.service.YearNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/number/year")
public class YearNumberController {
    @Autowired
    private YearNumberService yearNumberService;

    @GetMapping("/findAll")
    public List<YearNumber> findAll() {
        return yearNumberService.findAll();
    }

    @GetMapping("/findByYearNumber/{yearNumber}")
    public ResponseEntity<YearNumber> findByYearNumber(@PathVariable long yearNumber) {
        Optional<YearNumber> yearNumberFound = yearNumberService.findByYearNumber(yearNumber);
        return yearNumberFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/random")
    public ResponseEntity<YearNumber> findByYearNumberRandom() {
        List<YearNumber> yearNumberList = yearNumberService.findAll();
        List<Long> list = new ArrayList<>();
        for(YearNumber yearNumber : yearNumberList) {
            list.add(yearNumber.getYearNumber());
        }
        Long randomNumber = list.get((int) (Math.random() * list.size()));
        Optional<YearNumber> yearNumberFound = yearNumberService.findByYearNumber(randomNumber);
        return yearNumberFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/save")
    public ResponseEntity<YearNumber> save(@RequestBody YearNumber yearNumber) {
        return new ResponseEntity<>(yearNumberService.save(yearNumber), HttpStatus.CREATED);
    }

    @PutMapping("/update/{yearNumber}")
    public ResponseEntity<YearNumber> updateYearNumber (@PathVariable long yearNumber, @RequestBody YearNumber myNewYearNumber) {
        Optional<YearNumber> myYearNumber = yearNumberService.findByYearNumber(yearNumber);
        if (myYearNumber.isPresent()) {
            myNewYearNumber.setId(myYearNumber.get().getId());
            YearNumber updateYearNumber = yearNumberService.save(myNewYearNumber);
            return ResponseEntity.ok(updateYearNumber);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/byYearNumber/{yearNumber}")
    public ResponseEntity<YearNumber> deleteByYearNumber(@PathVariable long yearNumber) {
        yearNumberService.deleteByYearNumber(yearNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/byId/{id}")
    public ResponseEntity<YearNumber> deleteById(@PathVariable long id) {
        yearNumberService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
