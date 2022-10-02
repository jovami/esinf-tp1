package esinf.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Pais {

    private LinkedList<ProducaoAno> producaoAnual;

    private String nomePais;
    private int paisCodigo;

    public Pais(String nomePais, int paisCodigo) {
        this.producaoAnual = new LinkedList<>();

        setNomePais(nomePais);
        setCodigoPais(paisCodigo);
    }

    public Pais(String nome, int codigo, Collection<ProducaoAno> producoes) {
        this(nome, codigo);
        this.producaoAnual.addAll(producoes);
    }

    public Pais(String nome, int codigo, ProducaoAno... producoes) {
        this(nome, codigo, List.of(producoes));
    }

    private void setNomePais(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("erro: nome invalido para o pais");
        this.nomePais = nome;
    }

    private void setCodigoPais(int paisCodigo) {
        this.paisCodigo = paisCodigo;
    }

    public String getNomePais() {
        return nomePais;
    }

    public int getPaisCodigo() {
        return paisCodigo;
    }

    // LinkedList

    public boolean addAnoProducao(ProducaoAno ano) {
        if (ano == null)
            throw new IllegalArgumentException("erro: ano invalido");

        return this.producaoAnual.add(ano);
    }

    public boolean addAllAnos(Collection<ProducaoAno> producoes) {
        if (producoes == null || producoes.isEmpty())
            throw new IllegalArgumentException("erro: anos invalidos");

        return this.producaoAnual.addAll(producoes);
    }

    public ListIterator<ProducaoAno> getIteradorAnos() {
        return this.producaoAnual.listIterator();
    }

    public ListIterator<ProducaoAno> getIteradorAnos(int indice) {
        return this.producaoAnual.listIterator(indice);
    }

    // Overrides

    @Override
    public String toString() {
        return String.format("%s (%d)", this.nomePais, this.paisCodigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (o == null || this.getClass() != o.getClass())
            return false;

        Pais p = (Pais) o;

        return this.nomePais.equals(p.nomePais)
            && this.paisCodigo == p.paisCodigo;
    }

    @Override
    public int hashCode() {
        final int multiplier = 31;
        int result = 17;

        result = multiplier * result + this.paisCodigo;
        result = multiplier * result + this.nomePais.hashCode();
        result = multiplier * result + this.producaoAnual.hashCode();

        return result;
    }

}
