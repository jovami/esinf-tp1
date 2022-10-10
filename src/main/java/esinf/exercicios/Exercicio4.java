package esinf.exercicios;

import esinf.App;
import esinf.model.Pais;
import esinf.model.ProducaoAno;
import esinf.util.ListPrinter;
import esinf.util.Pair;

import java.util.*;

/**
 * Exercicio4
 */
public class Exercicio4 implements Runnable {
    private final App app;

    private final Comparator<Pair<Pais, Integer>> comparator =
            (o1, o2) -> Integer.compare(o2.getSecond(), o1.getSecond());


    public Exercicio4() {
        app = App.getInstance();
    }

    @Override
    public void run() {
        int idFrutoEscolhido = 486;
        printListProduction(getListMaxProduction(idFrutoEscolhido));

    }

    private <E> void printListProduction(List<E> listProduction) {
        String header = "\nPais --- Maior Crescimento\n";
        ListPrinter.print(listProduction, header, null);
    }

    public List<Pair<Pais, Integer>> getListMaxProduction(int idFrutoEscolhido) throws NullPointerException {
        Iterator<Pais> paisIter = app.getPaisStore().iterator();
        ArrayList<Pair<Pais, Integer>> listProduction = new ArrayList<>();

        while (paisIter.hasNext()) {
            Pais p = paisIter.next();

            Iterator<ProducaoAno> iter = p.iterator();
            int count = 0, maxCount = 0, previous = 0;


            while (iter.hasNext()) {
                ProducaoAno ano = iter.next();
                if (ano.hasProdFruto(idFrutoEscolhido)) {
                    int current = ano.getProducaoFruto(idFrutoEscolhido).getQuantidadeProducao();
                    if (current > previous) {
                        count++;
                        if (maxCount < count)
                            maxCount = count;
                    } else
                        count = 1;

                    previous = current;

                } else
                    count = 0;

            }
            maxCount--;


            if (maxCount > 0)
                listProduction.add(new Pair<>(p, maxCount));
        }
        listProduction.sort(comparator);

        return listProduction;

    }


}