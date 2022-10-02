package esinf.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;


public class ProducaoAno implements Comparable<ProducaoAno> {

    private HashMap <Integer, ProducaoFrutoPorPaisPorAno> prodAnual;

    // TODO: ver se int chega (provavelmente sim?)
    private LocalDate ano;

    public ProducaoAno(LocalDate ano) {
        // estimativa para reduzir hashing
        final int estimativa = 0x10;
        this.prodAnual = new HashMap<>(estimativa);

        this.ano = ano;
    }

    public ProducaoAno(int ano) {
        this(LocalDate.ofYearDay(ano, 1));
    }

    public int getAno() {
        return ano.getYear();
    }

    public Iterator<ProducaoFrutoPorPaisPorAno> getIterador() {
        return this.prodAnual.values().iterator();
    }

    @Override
    public int compareTo(ProducaoAno outro) {
        return Integer.compare(this.getAno(), outro.getAno());
    }
}
