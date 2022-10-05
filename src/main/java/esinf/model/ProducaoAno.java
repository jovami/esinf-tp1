package esinf.model;

import java.util.HashMap;
import java.util.Iterator;


public class ProducaoAno implements Comparable<ProducaoAno>, Iterable<ProducaoFrutoPorPaisPorAno> {

    // Key: FrutoID
    private HashMap <Integer, ProducaoFrutoPorPaisPorAno> prodAnual;
    private int quantidadeProdAnual;
    // TODO: ver se int chega (provavelmente sim?)
    private int ano;

    public ProducaoAno(int ano) {
        // estimativa para reduzir hashing
        final int estimativa = 0x10;
        this.prodAnual = new HashMap<>(estimativa);
        setProdAnual(0);
        this.ano = ano;

    }

    public ProducaoFrutoPorPaisPorAno getProducaoFruto(int frutoId) {
        return this.prodAnual.get(frutoId);
    }

    public boolean addProducaoFruto(Fruto fruto,int quantidadeProd){
        if (this.prodAnual.containsKey(fruto.getId()))
            return false; // Todo: exceção?, se já tivermos o registo de um fruto naquele ano, substituir?
        return this.prodAnual.put(fruto.getId(), new ProducaoFrutoPorPaisPorAno(fruto, quantidadeProd)) != null;
    }

    public int getProdAnual(){
        return quantidadeProdAnual;
    }

    private void setProdAnual(int quantidadeProdAnual){
        this.quantidadeProdAnual=quantidadeProdAnual;
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
