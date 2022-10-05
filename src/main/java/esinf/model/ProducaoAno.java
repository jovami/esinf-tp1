package esinf.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;


public class ProducaoAno implements Comparable<ProducaoAno> {

    private HashMap <Integer, ProducaoFrutoPorPaisPorAno> prodAnual;
    private int quantidadeProdAnual;
    // TODO: ver se int chega (provavelmente sim?)
    private LocalDate ano;

    public ProducaoAno(LocalDate ano) {
        // estimativa para reduzir hashing
        final int estimativa = 0x10;
        this.prodAnual = new HashMap<>(estimativa);
        setProdAnual(0);
        this.ano = ano;
        
    }


    public ProducaoAno(int ano) {
        this(LocalDate.ofYearDay(ano, 1));
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
