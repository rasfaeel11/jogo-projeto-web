package com.rafaelMelo.rpg_cyberpunk_api.service;

import java.util.Random;

import com.rafaelMelo.rpg_cyberpunk_api.model.*;

public class GameEngine {
    private Personagem jogador;
    private Personagem inimigoAtual;
    private CombatService combatService;
    private Random random;
    private double hpInicial = inimigoAtual.getHp();

    private int dificuldade;
    private Personagem[] inimigosCampanha = {
        new Drone("Corp Drone"),
        new Hacker("Corp Hacker"),
        new Mercenario("Corp Mercenario")
    };
    private int indiceInimigoAtual = 0; 

    public void iniciarJogo(String nomeJogador, int escolhaClasse, int dificuldade){
        this.dificuldade = dificuldade;
        this.combatService = new CombatService();
        this.random = new Random();

        if(escolhaClasse == 1) jogador = new CyberPunk(nomeJogador);
        if(escolhaClasse == 2) jogador = new NetRunner(nomeJogador);
        if(escolhaClasse == 3) jogador = new StreetSamurai(nomeJogador);
        if(escolhaClasse == 4) jogador = new Techie(nomeJogador);

        for(Personagem inimigo : inimigosCampanha){
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
    }
    public int turnoJogador(int escolha){
        switch (escolha) {
            case 1:
                return combatService.atacarFisico(jogador, inimigoAtual);
            case 2:
                return combatService.atacarCibernetico(jogador, inimigoAtual);
            case 3:
                return combatService.usarStim(jogador, 15);
        
            default:
                return 0;
        }
    }
    public int turnoInimigo(){
        double chance = Math.random();
        if(inimigoAtual.getHp() <= 0.30 * hpInicial && inimigoAtual.getStims() > 0 && chance < 0.3){
            return combatService.usarStim(inimigoAtual, 10);
        } else if(inimigoAtual.getEp() > 5 && chance < 0.7){
            return combatService.atacarCibernetico(inimigoAtual, jogador);
        } else {
            return combatService.atacarFisico(inimigoAtual, jogador);
        }
    }

   public Personagem getInimigoAtual() {
        if(indiceInimigoAtual < inimigosCampanha.length){
            return inimigosCampanha[indiceInimigoAtual];
        }
        return null;
    }
    public Personagem getJogador() { return jogador; }

    public boolean proximoInimigo(){
        indiceInimigoAtual++;
        if(indiceInimigoAtual < inimigosCampanha.length){
            inimigoAtual = inimigosCampanha[indiceInimigoAtual];
            return true;
        }
        return false;
    }
}


    
