package esinf.exercicios;

import esinf.App;
import esinf.model.Fruto;
import esinf.model.ProducaoAno;
import esinf.util.Pair;
import esinf.util.Triplet;
import java.util.*;


/**
 * Exercicio5
 */
public class Exercicio5 implements Runnable {

    private App app;

    public Exercicio5() {
        app = App.getInstance();
    }



    @Override
    public void run() {

        String estrutura = getEstruturaPedida(174);
        System.out.println(estrutura);

    }


    public String getEstruturaPedida(Integer idPais)
    {

        int i = idPais;

        final int maxFrutos = app.getFrutoStore().size();
        HashMap<Fruto, Pair<ProducaoAno, Integer>> minProducao = new HashMap<>(maxFrutos);
        HashMap<Fruto, Pair<ProducaoAno, Integer>> maxProducao = new HashMap<>(maxFrutos);

        app.getPaisStore().getPais(i).forEach(ano -> {
            ano.forEach(prod -> {
                Fruto f = prod.getFruto();
                int qtd = prod.getQuantidadeProducao();

                if (minProducao.get(f) == null || minProducao.get(f).getSecond() > qtd)
                        minProducao.put(f, new Pair<ProducaoAno,Integer>(ano, qtd));

                if (maxProducao.get(f) == null || maxProducao.get(f).getSecond() < qtd)
                        maxProducao.put(f, new Pair<ProducaoAno,Integer>(ano, qtd));
            });
        });

        Iterator<Fruto> iter = app.getFrutoStore().iterator();

        int maxSoFar = 0;
        Fruto frutoKey = null;

        while (iter.hasNext()) {
            Fruto f = iter.next();

            if(maxProducao.get(f) != null &&  minProducao.get(f) != null )
            {
                 int diff = maxProducao.get(f).getSecond() - minProducao.get(f).getSecond();


                if (diff > maxSoFar) {
                    maxSoFar = diff;
                    frutoKey = f;
                }
            }
        }

        Pair<Integer, Integer> anoMinMax = new Pair<>(minProducao.get(frutoKey).getFirst().getAno(), maxProducao.get(frutoKey).getFirst().getAno());

        Triplet<Pair<Integer, Integer>, Fruto, Integer> resposta = new Triplet<>(anoMinMax, frutoKey, maxSoFar);


        String estrutura = "["+resposta.getFirst().getFirst()+"/"+resposta.getFirst().getSecond()+ "," + resposta.getSecond().getNome() + "," + resposta.getThird() + "]";

        return estrutura;

    }

}
