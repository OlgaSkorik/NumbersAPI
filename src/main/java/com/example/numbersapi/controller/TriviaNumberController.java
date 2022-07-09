package com.example.numbersapi.controller;

import com.example.numbersapi.entity.TriviaNumber;
import com.example.numbersapi.service.TriviaNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/number/trivia")
public class TriviaNumberController {

    @Autowired
    private TriviaNumberService triviaNumberService;

    @GetMapping("/findAll")
    public List<TriviaNumber> findAll() {
        return triviaNumberService.findAll();
    }

    @GetMapping("/findByTriviaNumber/{triviaNumber}")
    public ResponseEntity<TriviaNumber> findByTriviaNumber(@PathVariable long triviaNumber) {
        Optional<TriviaNumber> byTriviaNumber = triviaNumberService.findByTriviaNumber(triviaNumber);
        return byTriviaNumber.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/random")
    public ResponseEntity<TriviaNumber> findByTriviaNumberRandom() {
        List<TriviaNumber> triviaNumberList = triviaNumberService.findAll();
        List<Long> list = new ArrayList<>();
        for(TriviaNumber triviaNumber : triviaNumberList) {
            list.add(triviaNumber.getTriviaNumber());
        }
        Long randomNumber = list.get((int) (Math.random() * list.size()));
        Optional<TriviaNumber> byTriviaNumber = triviaNumberService.findByTriviaNumber(randomNumber);
        return byTriviaNumber.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/save")
    public ResponseEntity<TriviaNumber> save(@RequestBody TriviaNumber triviaNumber) {
        return new ResponseEntity<>(triviaNumberService.save(triviaNumber), HttpStatus.CREATED);
    }

    @PutMapping("/update/{triviaNumber}")
    public ResponseEntity<TriviaNumber> updateTriviaNumber (@PathVariable long triviaNumber, @RequestBody TriviaNumber myNewTriviaNumber) {
        Optional<TriviaNumber> myTriviaNumber = triviaNumberService.findByTriviaNumber(triviaNumber);
        if (myTriviaNumber.isPresent()) {
            myNewTriviaNumber.setId(myTriviaNumber.get().getId());
            TriviaNumber updateTriviaNumber = triviaNumberService.save(myNewTriviaNumber);
            return ResponseEntity.ok(updateTriviaNumber);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/byTriviaNumber/{triviaNumber}")
    public ResponseEntity<TriviaNumber> deleteByTriviaNumber(@PathVariable long triviaNumber) {
        triviaNumberService.deleteByTriviaNumber(triviaNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/byId/{id}")
    public ResponseEntity<TriviaNumber> deleteById(@PathVariable long id) {
        triviaNumberService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
