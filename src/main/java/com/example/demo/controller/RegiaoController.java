package com.example.demo.controller;

import com.example.demo.entity.Regiao;
import com.example.demo.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RegiaoController {

    @Autowired
    private RegiaoRepository regiaoRepository;

    @GetMapping("/regiao")
    public List<Regiao> getRegiao(){
        return regiaoRepository.findAll();
    }
    @GetMapping("/regiao/{id}")
    public ResponseEntity<?> GetRegiaoById(@PathVariable Long id){
        Optional<Regiao> regiaoEscolhidaOptional = regiaoRepository.findById(id);
        if(regiaoEscolhidaOptional.isPresent()){
            Regiao regiaoEscolhida = regiaoEscolhidaOptional.get();
            return new ResponseEntity<>(regiaoEscolhida, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("regiao/novo")
    public Regiao putRegiao(@RequestBody Regiao regiao){
        return regiaoRepository.save(regiao);
    }
    @DeleteMapping("regiao/delete/{id}")
    public void deleteRegiaoByID(@PathVariable Long id){
        regiaoRepository.deleteById(id);
    }


}
