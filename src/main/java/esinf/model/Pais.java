package esinf.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Pais implements Iterable<ProducaoAno> {

    private final TreeMap<Integer, ProducaoAno> producaoAnual;

    private String nomePais;
    private int paisCodigo;
    private int prodTotal;


    public Pais(String nomePais, int paisCodigo) {
        this.producaoAnual = new TreeMap<>(Integer::compareTo);

        this.prodTotal = 0;
        setNomePais(nomePais);
        setCodigoPais(paisCodigo);
    }

    public Pais(String nome, int codigo, Collection<ProducaoAno> producoes) {
        this(nome, codigo);
        producoes.forEach(this::addAnoProducao);
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
        return this.nomePais;
    }

    public int getPaisCodigo() {
        return this.paisCodigo;
    }

    public int getProdTotal() {
        return this.prodTotal;
    }

    private void incrementProducao(int prodTotal) {
        this.prodTotal += prodTotal;
    }

    // TreeSet
    public boolean containsAnoProducao(int ano) {
        return this.producaoAnual.get(ano) != null;
    }

    public void addAnoProducao(ProducaoAno ano) {
        if (ano == null)
            throw new IllegalArgumentException("erro: ano invalido");

        ProducaoAno oldAno = this.producaoAnual.put(ano.getAno(), ano);

        int qtd = ano.getProdAnual();
        incrementProducao(qtd - (oldAno != null ? oldAno.getProdAnual() : 0));
    }

    public ProducaoAno createAnoProducao(int ano) {
        return new ProducaoAno(ano);
    }

    public void addAllAnos(Collection<ProducaoAno> producoes) {
        if (producoes == null || producoes.isEmpty())
            throw new IllegalArgumentException("erro: anos invalidos");

        producoes.forEach(this::addAnoProducao);
    }

    public ProducaoAno getProducaoAno(Integer ano){
        return producaoAnual.get(ano);
    }

    // Overrides

	@Override
	public Iterator<ProducaoAno> iterator() {
		return this.producaoAnual.values().iterator();
	}


    public Stream<ProducaoAno> stream() {
        return this.producaoAnual.values().stream();
    }

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
