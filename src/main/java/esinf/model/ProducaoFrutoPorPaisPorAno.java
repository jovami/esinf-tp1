package esinf.model;

public class ProducaoFrutoPorPaisPorAno {
    private Fruto fruto;
    private int quantidadeProducao;

    public ProducaoFrutoPorPaisPorAno(Fruto fruto,int quantidadeProducao) {
        setFruto(fruto);
        setQuantidadeProducao(quantidadeProducao);
    }

    public Fruto getFruto() {
        return fruto;
    }

    private void setFruto(Fruto fruto) {
        this.fruto = fruto;
    }

    public int getQuantidadeProducao() {
        return quantidadeProducao;
    }

    private void setQuantidadeProducao(int quantidadeProducao) {
        this.quantidadeProducao = quantidadeProducao;
    }
}
