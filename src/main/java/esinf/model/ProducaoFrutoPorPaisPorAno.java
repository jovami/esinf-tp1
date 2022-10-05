package esinf.model;

//TODO: mudar o nome desta classe
public class ProducaoFrutoPorPaisPorAno {
    private Fruto fruto;
    private int quantidadeProducao;

    public Fruto getFruto() {
        return fruto;
    }

    public void setFruto(Fruto fruto) {
        this.fruto = fruto;
    }

    public int getQuantidadeProducao() {
        return quantidadeProducao;
    }

    public void setQuantidadeProducao(int quantidadeProducao) {
        this.quantidadeProducao = quantidadeProducao;
    }

    public ProducaoFrutoPorPaisPorAno(Fruto fruto,int quantidadeProducao){
        setFruto(fruto); 
        setQuantidadeProducao(quantidadeProducao);
  }
}