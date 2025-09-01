package com.rafaelMelo.rpg_cyberpunk_api.service;
import java.util.Random;

import com.rafaelMelo.rpg_cyberpunk_api.model.*;

public class CombatService {
    Random random = new Random();

    private static final int EP_GASTO = 8;
    private static final int VALOR_CURA_STIM = 20;


    public int atacarFisico(Personagem Atacante, Personagem Alvo){
        int d20 = random.nextInt(20) + 1;
        if(d20 == 1) return 0;
        int dano = (Atacante.getDanoBase() + Atacante.getForca() + d20 - Alvo.getDefesaFisica());
        if(dano < 0) dano = 0;
        if(d20 == 20) dano *= 2;
        Alvo.receberDanoFisico(dano);
        return dano;
    }
    public int atacarCibernetico(Personagem Atacante, Personagem Alvo){
        if(Atacante.getEp() < EP_GASTO) return 0;
        Atacante.gastarEp(EP_GASTO);
        int d20 = random.nextInt(20) + 1;
        if(d20 == 1) return 0;
        int danoMagico = (Atacante.getDanoBase() + Atacante.getInteligencia() + d20 - Alvo.getDefesaCibernetica());
        if(danoMagico < 0) danoMagico = 0;
        if(d20 == 20) danoMagico *= 2;
        Alvo.receberDanoCibernetico(danoMagico);
        return danoMagico;
    }

    public int usarStim(Personagem personagem, int valorCura){
        if(personagem.getStims() < 1){
            return 0;
        } else {
            personagem.curar(VALOR_CURA_STIM);
        }
        return valorCura;
        

    }
}
