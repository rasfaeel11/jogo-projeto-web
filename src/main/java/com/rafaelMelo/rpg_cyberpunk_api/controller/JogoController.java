package com.rafaelMelo.rpg_cyberpunk_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

@RequestMapping("/jogo")
public class JogoController {

  
    @GetMapping("/iniciar")
    public String iniciarJogo() {
        return "Jogo iniciado com sucesso! (Isso veio da nossa API!)";
    }
}
