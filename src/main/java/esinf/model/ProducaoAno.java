package esinf.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;


public class ProducaoAno implements Comparable<ProducaoAno>, Iterable<ProducaoFrutoPorPaisPorAno> {

    private HashMap <Integer, ProducaoFrutoPorPaisPorAno> prodAnual;

    // TODO: ver se int chega (provavelmente sim?)
    private int ano;

    public ProducaoAno(int ano) {
        // estimativa para reduzir hashing
        final int estimativa = 0x10;
        this.prodAnual = new HashMap<>(estimativa);

        this.ano = ano;
    }

    // TODO: remover isto
    @Deprecated
    public ProducaoAno(LocalDate data) {
        this(data.getYear());
    }

    public int getAno() {
        return this.ano;
    }

    public Iterator<ProducaoFrutoPorPaisPorAno> iterator() {
        return this.prodAnual.values().iterator();
    }

    @Override
    public int compareTo(ProducaoAno outro) {
        return Integer.compare(this.getAno(), outro.getAno());
    }
}
