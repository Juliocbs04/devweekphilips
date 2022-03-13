package com.example.demo.controller;

import com.example.demo.entity.FaixaEtaria;
import com.example.demo.entity.IncidenciaExame;
import com.example.demo.repository.FaixaEtariaRepository;
import com.example.demo.repository.IncidenciaExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FaixaEtariaController {
    @Autowired
    private FaixaEtariaRepository faixaEtariaRepository;

    @GetMapping("/faixaetaria")
    public List<FaixaEtaria> getIncidenciaExame(){
        return faixaEtariaRepository.findAll();
    }
    @GetMapping("/faixaetaria/{id}")
    public ResponseEntity<?> GetIncidenciaById(@PathVariable Long id){
        try {
            Optional<FaixaEtaria> faixaEscolhidaOptional = faixaEtariaRepository.findById(id);
            if (faixaEscolhidaOptional.isPresent()) {
                FaixaEtaria i = faixaEscolhidaOptional.get();
                return new ResponseEntity<>(i, HttpStatus.OK);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("faixaetaria/novo")
    public FaixaEtaria putIncidencia(@RequestBody FaixaEtaria ie){
        return faixaEtariaRepository.save(ie);
    }
    @DeleteMapping("faixaetaria/delete/{id}")
    public void deleteIncidenciaByID(@PathVariable Long id){
        faixaEtariaRepository.deleteById(id);
    }


}
