package esinf.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Pais implements Iterable<ProducaoAno> {

    private TreeSet<ProducaoAno> producaoAnual;

    private String nomePais;
    private int paisCodigo;

    public Pais(String nomePais, int paisCodigo) {
        this.producaoAnual = new TreeSet<>(ProducaoAno::compareTo);

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

    // TreeSet

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

    // Overrides

	@Override
	public Iterator<ProducaoAno> iterator() {
		return this.producaoAnual.iterator();
	}

    public Iterator<ProducaoAno> iteradorDecrescente() {
        return this.producaoAnual.descendingIterator();
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
