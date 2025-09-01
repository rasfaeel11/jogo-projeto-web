package com.rafaelMelo.rpg_cyberpunk_api.service;

import org.springframework.stereotype.Service;
import com.rafaelMelo.rpg_cyberpunk_api.dto.GameStateDTO;
import com.rafaelMelo.rpg_cyberpunk_api.model.*;

@Service
public class GameEngine {
    private Personagem jogador;
    private Personagem inimigoAtual;
    private CombatService combatService;
    private double hpInicialInimigo;

    private Personagem[] inimigosCampanha = {
        new Drone("Corp Drone"),
        new Hacker("Corp Hacker"),
        new Mercenario("Corp Mercenario")
    };
    private int indiceInimigoAtual = 0;

    public GameEngine(CombatService combatService) {
        this.combatService = combatService;
    }

    public GameStateDTO iniciarJogo(String nomeJogador, int escolhaClasse, int dificuldade) {

        this.indiceInimigoAtual = 0;

        switch (escolhaClasse) {
            case 1: jogador = new CyberPunk(nomeJogador); break;
            case 2: jogador = new NetRunner(nomeJogador); break;
            case 3: jogador = new StreetSamurai(nomeJogador); break;
            case 4: jogador = new Techie(nomeJogador); break;
            default: jogador = new StreetSamurai(nomeJogador); break;
        }

        for (Personagem inimigo : inimigosCampanha) {
             switch (dificuldade) {
                case 1:
                    inimigo.setHp((int)(inimigo.getHp() * 0.9));
                    inimigo.setDanoBase((int)(inimigo.getDanoBase() * 0.9));

                    break;
                case 2:
                    inimigo.setHp((int)(inimigo.getHp() * 1));
                    inimigo.setDanoBase((int)inimigo.getDanoBase() * 1);
                    break;
                case 3:
                    inimigo.setHp((int)(inimigo.getHp() * 1.1));
                    inimigo.setDanoBase((int)(inimigo.getDanoBase() * 1.1));
                    break;
                case 4:
                    inimigo.setHp((int)(inimigo.getHp() * 1.15));
                    inimigo.setDanoBase((int)(inimigo.getDanoBase() * 1.15));
                    break;
                default:
                    break;
            }
        }

        inimigoAtual = getInimigoAtual();
        hpInicialInimigo = inimigoAtual.getHp();
        return criarEstadoAtualDoJogo("O jogo começou! " + inimigoAtual.getNome() + " apareceu.");
    }

    public GameStateDTO executarTurno(int escolhaAcao) {
        turnoJogador(escolhaAcao);
        
        if (inimigoAtual.getHp() <= 0) {
            indiceInimigoAtual++;
            inimigoAtual = getInimigoAtual();

            if (inimigoAtual == null) {
                return criarEstadoAtualDoJogo("Você derrotou todos os inimigos! Vitória!");
            }

            hpInicialInimigo = inimigoAtual.getHp();
            return criarEstadoAtualDoJogo("Inimigo derrotado! O próximo é " + inimigoAtual.getNome());
        }


        turnoInimigo();

        if (jogador.getHp() <= 0) {
            return criarEstadoAtualDoJogo("Você foi derrotado! Fim de jogo.");
        }

        String mensagemTurno = "Seu turno foi processado. O inimigo contra-atacou!";
        return criarEstadoAtualDoJogo(mensagemTurno);
    }

    private GameStateDTO criarEstadoAtualDoJogo(String mensagem) {
        GameStateDTO estado = new GameStateDTO();
        estado.setNomeJogador(jogador.getNome());
        estado.setHpJogador((int) jogador.getHp());
        estado.setEpJogador(jogador.getEp());
        estado.setStimsJogador(jogador.getStims());

        if (inimigoAtual != null) {
            estado.setNomeInimigo(inimigoAtual.getNome());

            estado.setHpInimigo((int) inimigoAtual.getHp());
        }

        estado.setMensagem(mensagem);
        return estado;
    }
    

    private int turnoJogador(int escolha) {
        switch (escolha) {
            case 1: return combatService.atacarFisico(jogador, inimigoAtual);
            case 2: return combatService.atacarCibernetico(jogador, inimigoAtual);
            case 3: return combatService.usarStim(jogador, 15);
            default: return 0;
        }
    }


    private int turnoInimigo() {
        double chance = Math.random();

        if (inimigoAtual.getHp() <= 0.30 * hpInicialInimigo && inimigoAtual.getStims() > 0 && chance < 0.3) {
            return combatService.usarStim(inimigoAtual, 10);
        } else if (inimigoAtual.getEp() > 5 && chance < 0.7) {
            return combatService.atacarCibernetico(inimigoAtual, jogador);
        } else {
            return combatService.atacarFisico(inimigoAtual, jogador);
        }
    }

    public Personagem getInimigoAtual() {
        if (indiceInimigoAtual < inimigosCampanha.length) {
            return inimigosCampanha[indiceInimigoAtual];
        }
        return null;
    }
}