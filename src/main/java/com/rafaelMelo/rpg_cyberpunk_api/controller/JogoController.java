package com.rafaelMelo.rpg_cyberpunk_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelMelo.rpg_cyberpunk_api.dto.AcaoRequestDTO;
import com.rafaelMelo.rpg_cyberpunk_api.dto.GameStateDTO;
import com.rafaelMelo.rpg_cyberpunk_api.dto.IniciarJogoRequestDTO;
import com.rafaelMelo.rpg_cyberpunk_api.service.GameEngine;

@RestController
@RequestMapping("/jogo")
public class JogoController {

    private final GameEngine gameEngine;

    public JogoController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }


    @PostMapping("/iniciar")
    public GameStateDTO iniciarJogo(@RequestBody IniciarJogoRequestDTO request) {
        return gameEngine.iniciarJogo(
            request.getNomeJogador(),
            request.getEscolhaClasse(),
            request.getDificuldade()
        );
    }

    @PostMapping("/acao")
    public GameStateDTO executarAcao(@RequestBody AcaoRequestDTO request) {
        return gameEngine.executarTurno(request.getEscolhaAcao());
    }
}