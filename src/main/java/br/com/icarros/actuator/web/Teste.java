package br.com.icarros.actuator.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teste")
public class Teste {

    @GetMapping
    public ResponseEntity<String> listar() {
        return ResponseEntity.ok("funcionou");
    }
}
