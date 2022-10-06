package esinf.model;

import java.util.HashMap;
import java.util.Iterator;

public class ProducaoAno implements Comparable<ProducaoAno>, Iterable<ProducaoFrutoPorPaisPorAno> {

    // Key: FrutoID
    private HashMap<Integer, ProducaoFrutoPorPaisPorAno> prodAnual;

    private int quantidadeProdAnual;
    private int ano;

    public ProducaoAno(int ano) {
        // estimativa para reduzir hashing
        final int estimativa = 0x20;

        this.prodAnual = new HashMap<>(estimativa);
        this.quantidadeProdAnual = 0;
        this.ano = ano;
    }

    public ProducaoFrutoPorPaisPorAno getProducaoFruto(int frutoId) {
        return this.prodAnual.get(frutoId);
    }

    public boolean addProducaoFruto(Fruto fruto, int quantidadeProd) {
        var prodFruto = new ProducaoFrutoPorPaisPorAno(fruto, quantidadeProd);
        boolean ok = this.prodAnual.putIfAbsent(fruto.getId(), prodFruto) == null;

        if (ok)
            incrementProdAnual(prodFruto.getQuantidadeProducao());
        return ok;
    }

    private void incrementProdAnual(int prod) {
        this.quantidadeProdAnual += prod;
    }

    public int getProdAnual() {
        return this.quantidadeProdAnual;
    }

    public int getAno() {
        return this.ano;
    }

    @Override
    public Iterator<ProducaoFrutoPorPaisPorAno> iterator() {
        return this.prodAnual.values().iterator();
    }

    @Override
    public int compareTo(ProducaoAno outro) {
        return Integer.compare(this.getAno(), outro.getAno());
    }
}
