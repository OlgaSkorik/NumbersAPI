package com.example.numbersapi.controller;

import com.example.numbersapi.entity.MathNumber;
import com.example.numbersapi.service.MathNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/number/math")
public class MathNumberController {

    @Autowired
    private MathNumberService mathNumberService;

    @GetMapping("/findAll")
    public List<MathNumber> findAll() {
        return mathNumberService.findAll();
    }

    @GetMapping("/findByMathNumber/{mathNumber}")
    public ResponseEntity<MathNumber> findByMathNumber(@PathVariable long mathNumber) {
        Optional<MathNumber> mathNumberFound = mathNumberService.findByMathNumber(mathNumber);
        return mathNumberFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/random")
    public ResponseEntity<MathNumber> findByMathNumberRandom() {
        List<MathNumber> mathNumberList = mathNumberService.findAll();
        List<Long> list = new ArrayList<>();
        for(MathNumber mathNumber : mathNumberList) {
            list.add(mathNumber.getMathNumber());
        }
        Long randomNumber = list.get((int) (Math.random() * list.size()));
        Optional<MathNumber> mathNumberFound = mathNumberService.findByMathNumber(randomNumber);
        return mathNumberFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/save")
    public ResponseEntity<MathNumber> save(@RequestBody MathNumber mathNumber) {
        return new ResponseEntity<>(mathNumberService.save(mathNumber), HttpStatus.CREATED);
    }

    @PutMapping("/update/{mathNumber}")
    public ResponseEntity<MathNumber> updateMathNumber (@PathVariable long mathNumber, @RequestBody MathNumber myNewMathNumber) {
        Optional<MathNumber> myMathNumber = mathNumberService.findByMathNumber(mathNumber);
        if (myMathNumber.isPresent()) {
            myNewMathNumber.setId(myMathNumber.get().getId());
            MathNumber updateMathNumber = mathNumberService.save(myNewMathNumber);
            return ResponseEntity.ok(updateMathNumber);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/byMathNumber/{mathNumber}")
    public ResponseEntity<MathNumber> deleteByMathNumber(@PathVariable long mathNumber) {
        mathNumberService.deleteByMathNumber(mathNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/byId/{id}")
    public ResponseEntity<MathNumber> deleteById(@PathVariable long id) {
        mathNumberService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
