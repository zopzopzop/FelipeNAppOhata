package com.example.relativeleyout.dao;

import com.example.relativeleyout.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private  final  static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeId = 1;

    //recebe os constrututores do meu cormulário para guardados.
    public void salva(Personagem personagemSalvo) {
        personagemSalvo.setId(contadorDeId);

        personagens.add(personagemSalvo);
    }

    //selheciona o personagem escolhido
public void edita(Personagem personagem){
        Personagem personagemEscolhido = null;
        for (Personagem p: personagens){
            if(p.getId()== personagem.getId()){
                personagemEscolhido = p;
            }

            if (personagemEscolhido != null){
                int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
                personagens.set(posicaoDoPersonagem, personagem);
            }
        }

}
    //retorna os personagens salvor no meu personagen dao
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
}
