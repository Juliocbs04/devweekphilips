package com.example.demo.controller;

import com.example.demo.entity.IncidenciaExame;
import com.example.demo.entity.Regiao;
import com.example.demo.repository.IncidenciaExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IncidenciaController {

    @Autowired
    private IncidenciaExameRepository incidenciaRepository;

    @GetMapping("/incidencias")
    public ResponseEntity<List<IncidenciaExame>> getIncidenciaExame(){
        List<IncidenciaExame> listaIncidencia = incidenciaRepository.findAll();
        if(listaIncidencia.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaIncidencia, HttpStatus.OK);
    }
    @GetMapping("/inicidencia/{id}")
    public ResponseEntity<?> GetIncidenciaById(@PathVariable Long id){
        try {

            Optional<IncidenciaExame> regiaoEscolhidaOptional = incidenciaRepository.findById(id);
            if (regiaoEscolhidaOptional.isPresent()) {
                IncidenciaExame i = regiaoEscolhidaOptional.get();
                return new ResponseEntity<>(i, HttpStatus.OK);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("incidencia/novo")
    public IncidenciaExame newIncidencia(@RequestBody IncidenciaExame ie){
        return incidenciaRepository.save(ie);
    }
    @DeleteMapping("incidencia/delete/{id}")
    public void deleteIncidenciaByID(@PathVariable Long id){
        incidenciaRepository.deleteById(id);
    }


}
