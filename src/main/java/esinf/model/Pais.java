package esinf.model;

import java.util.LinkedList;

public class Pais {

    //TODO: methods to operate this Linked List
    private LinkedList<ProducaoAno> producaoAnual;

    private String nomePais;
    private int paisCodigo;

    public Pais(String nomePais, int paisCodigo){
        setNomePais(nomePais);
        setoCodigoPais(paisCodigo);
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String paisNome) {
        this.nomePais = paisNome;
    }

    public int getPaisCodigo() {
        return paisCodigo;
    }

    public void setoCodigoPais(int paisCodigo) {
        this.paisCodigo = paisCodigo;
    }
    
}
