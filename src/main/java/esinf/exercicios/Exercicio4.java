package esinf.exercicios;

import esinf.App;
import esinf.model.Fruto;
import esinf.model.Pais;
import esinf.model.ProducaoAno;
import esinf.model.ProducaoFrutoPorPaisPorAno;
import esinf.util.ListPrinter;
import esinf.util.Pair;

import java.util.*;

/**
 * Exercicio4
 */
public class Exercicio4 implements Runnable {
    private App app;

    private Comparator<Pair<Pais, Integer>> comparator =
            (o1, o2) -> Integer.compare(o2.getSecond(), o1.getSecond());


    public Exercicio4() {
        app = App.getInstance();
    }

    @Override
    public void run() {
        int idFrutoEscolhido = 515;
        frutoId(idFrutoEscolhido);

    }

    private void frutoId(int idFrutoEscolhido) {
        Iterator<Pais> paisIter = app.getPaisStore().iterator();
        ArrayList<Pair<Pais, Integer>> listProduction = new ArrayList<>();

        while (paisIter.hasNext()) {
            Pais p = paisIter.next();

            Iterator<ProducaoAno> iter = p.iterator();
            int count = 0, maxCount = 0;
            if (iter.hasNext()) {
                int previous = iter.next().getProducaoFruto(idFrutoEscolhido).getQuantidadeProducao();

                while (iter.hasNext()) {
                    ProducaoAno ano = iter.next();
                    int current = ano.getProducaoFruto(idFrutoEscolhido).getQuantidadeProducao();
                    if (current > previous) {
                        previous = current;
                        count++;
                        if (maxCount < count)
                            maxCount = count;

                    }

                }
                listProduction.add(new Pair<Pais, Integer>(p, maxCount));
            }

            listProduction.sort(comparator);


        }
        String header = "\nPais --- Maior Crescimento\n";
        ListPrinter.print(listProduction, header, null);

    }

}