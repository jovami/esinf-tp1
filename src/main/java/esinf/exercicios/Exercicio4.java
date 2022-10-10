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
    private App app;

    private Comparator<Pair<Pais, Integer>> comparator =
            (o1, o2) -> Integer.compare(o2.getSecond(), o1.getSecond());


    public Exercicio4() {
        app = App.getInstance();
    }

    @Override
    public void run() {
        int idFrutoEscolhido = 486;
        printListProduction(getListMaxProduction(idFrutoEscolhido));

    }

    private void printListProduction(List listProduction) {
        String header = "\nPais --- Maior Crescimento\n";
        ListPrinter.print(listProduction, header, null);
    }

    public List getListMaxProduction(int idFrutoEscolhido) throws NullPointerException {
        Iterator<Pais> paisIter = app.getPaisStore().iterator();
        ArrayList<Pair<Pais, Integer>> listProduction = new ArrayList<>();

        while (paisIter.hasNext()) {
            Pais p = paisIter.next();

            Iterator<ProducaoAno> iter = p.iterator();
            Boolean flag;
            int count = 0, maxCount = 0,previous=0;
            if (iter.hasNext()) {
                //previous = iter.next().getProducaoFruto(idFrutoEscolhido).getQuantidadeProducao();
                do {
                    try {
                        previous = iter.next().getProducaoFruto(idFrutoEscolhido).getQuantidadeProducao();
                        flag=false;
                    } catch (NullPointerException e) {
                        iter = paisIter.next().iterator();
                        flag=true;
                    }
                }while (flag);


                while (iter.hasNext()) {
                    ProducaoAno ano = iter.next();
                    int current = ano.getProducaoFruto(idFrutoEscolhido).getQuantidadeProducao();
                    if (current > previous) {
                        count++;
                        if (maxCount < count)
                            maxCount = count;

                    }else
                        count=0;
                    previous = current;

                }
                listProduction.add(new Pair<Pais, Integer>(p, maxCount));
            }

            listProduction.sort(comparator);


        }
        return listProduction;

    }


}