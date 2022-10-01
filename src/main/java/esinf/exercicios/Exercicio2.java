package esinf.exercicios;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import esinf.App;
import esinf.model.Pais;
import esinf.util.ListPrinter;
import esinf.util.Triplet;

/**
 * Exercicio2
 */
public class Exercicio2 implements Runnable {

    private App app;

    private Comparator<Triplet<Pais, Integer, Integer>> cmpA =
        (Triplet<Pais, Integer, Integer> t1, Triplet<Pais, Integer, Integer> t2) ->
        Integer.compare(t1.getSecond(), t2.getSecond());

    private Comparator<Triplet<Pais, Integer, Integer>> cmpB =
        (Triplet<Pais, Integer, Integer> t1, Triplet<Pais, Integer, Integer> t2) ->
        Integer.compare(t2.getThird(), t1.getThird());

    public Exercicio2() {
        app = App.getInstance();
    }

    @Override
    public void run() throws RuntimeException
    {
        int id = 12;
        int qtd = 200;

        List<Triplet<Pais, Integer, Integer>> filtered;

        filtered = aaaa(id, qtd);

        var alineaA = sortList(filtered, cmpA);

        // filtered already got sorted above
        var alineaB = sortList(filtered, cmpB);

        ListPrinter.print(alineaA, "Alinea a)", null);
        System.out.printf("\n--------------------\n");
        ListPrinter.print(alineaB, "Alinea b)", null);
    }

    public List<Triplet<Pais, Integer, Integer>>
        aaaa(int frutoId, int quantidade)
    {
        var l = new LinkedList<Triplet<Pais, Integer, Integer>>();
        Iterator<Pais> paisIter = app.getPaisStore().getIteradorPais();

        if (!paisIter.hasNext())
            throw new RuntimeException("erro: nao ha paises armazenados");

        paisIter.forEachRemaining((Pais p) -> {
            var iter = p.getIteradorAnos();
            while (iter.hasNext()) {
                var ano = iter.next();
                int qtd = 0/* ano.getFruto(frutoId).getProducao()*/;
                if (qtd >= quantidade) {
                    l.add(new Triplet<Pais,Integer,Integer>(p, ano.getAno(), qtd));
                    return;
                }
            }
        });

        return l;
    }

    /* T type alias */
    public <T extends Triplet<Pais, Integer, Integer>> List<Pais>
        sortList(List<T> lst, Comparator<T> cmp)
    {
        var result = new Vector<Pais>();
        Collections.sort(lst, cmp);

        lst.forEach((T t) -> result.add(t.getFirst()));
        return result;
    }

    // Exercicio aqui
}
